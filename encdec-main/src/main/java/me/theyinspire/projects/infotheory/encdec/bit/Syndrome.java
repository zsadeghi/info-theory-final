package me.theyinspire.projects.infotheory.encdec.bit;

import me.theyinspire.projects.infotheory.encdec.matrix.Matrix;

import java.util.List;
import java.util.stream.IntStream;

import static me.theyinspire.projects.infotheory.encdec.bit.BitMatrix.asBlock;

public class Syndrome {

    private final Matrix<Bit> initial;
    private final List<Matrix<Bit>> values;

    public Syndrome(final Matrix<Bit> initial, final List<Matrix<Bit>> values) {
        this.initial = initial;
        this.values = values;
    }

    public Matrix<Bit> initial() {
        return initial;
    }

    public List<Matrix<Bit>> values() {
        return values;
    }

    public int size() {
        return values.size();
    }

    public Matrix<Bit> values(int index) {
        return values.get(index);
    }

    @Override
    public String toString() {
        return asBlock(initial) + " = -1\n" +
                IntStream.range(0, size())
                .mapToObj(index -> asBlock(values(index)) + " = " + index)
                .reduce((first, second) -> first + "\n" + second)
                .orElse("");
    }

}
