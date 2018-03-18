package me.theyinspire.projects.infotheory.encdec.bit;

import me.theyinspire.projects.infotheory.encdec.matrix.ArrayMatrix;
import me.theyinspire.projects.infotheory.encdec.matrix.Matrix;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("WeakerAccess")
public class BitMatrix {

    public static Matrix<Bit> create(int rows, int cols) {
        return new ArrayMatrix<>(rows, cols);
    }

    public static Matrix<Bit> zero(int rows, int cols) {
        final Matrix<Bit> matrix = create(rows, cols);
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                matrix.set(i, j, Bit.ZERO);
            }
        }
        return matrix;
    }

    public static Matrix<Bit> random(int rows, int cols) {
        final Matrix<Bit> matrix = create(rows, cols);
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.set(i, j, Bit.values()[random.nextInt(2)]);
            }
        }
        return matrix;
    }

    public static Matrix<Bit> identity(int dimension) {
        final Matrix<Bit> matrix = create(dimension, dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix.set(i, j, i == j ? Bit.ONE : Bit.ZERO);
            }
        }
        return matrix;
    }

    public static Matrix<Bit> encode(Matrix<Bit> message, Matrix<Bit> generator, int n, int k) {
        if (message.rows() != 1) {
            throw new IllegalArgumentException("Message has to be a row matrix");
        }
        if (message.cols() < k) {
            throw new IllegalArgumentException("Message has to have " + k + " bits, but it has " + message.cols()
                                                       + " bits");
        }
        final Matrix<Bit> temporary = zero(1, n);
        for (int i = 0; i < n; i++) {
            Bit newBit = temporary.get(0, i);
            for (int j = 0; j < k; j++) {
                if (i >= generator.cols()) {
                    break;
                }
                final Bit currentBit = newBit;
                final Bit messageBit = message.get(0, j);
                final Bit generatorBit = generator.get(j, i);
                newBit = currentBit.add(messageBit.multiply(generatorBit));
            }
            temporary.set(0, i, newBit);
        }
        return temporary;
    }

    public static Syndrome syndrome(Matrix<Bit> message, Matrix<Bit> generator,
                                             Matrix<Bit> transposedCheck, int n, int k) {
        final List<Matrix<Bit>> list = new ArrayList<>(n + 1);
        final Matrix<Bit> code = encode(message, generator, n, k);
        final Matrix<Bit> initial = encode(code, transposedCheck, k, n);
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                code.set(0, i - 1, Bit.ZERO);
            }
            code.set(0, i, Bit.ONE);
            list.add(encode(code, transposedCheck, k, n));
        }
        return new Syndrome(initial, list);
    }

    public static String asBlock(Matrix<Bit> bitMatrix) {
        return IntStream.range(0, bitMatrix.rows())
                 .mapToObj(row -> IntStream.range(0, bitMatrix.cols())
                          .mapToObj(col -> bitMatrix.get(row, col))
                          .map(String::valueOf)
                          .reduce((first, second) -> first + second)
                          .orElse(""))
                 .reduce((first, second) -> first + "\n" + second)
                 .orElse("");
    }

}
