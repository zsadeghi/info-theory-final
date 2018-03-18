package me.theyinspire.projects.infotheory.encdec.matrix;

import java.util.Arrays;
import java.util.Objects;

public class ArrayMatrix<E> implements Matrix<E> {

    private final Object[][] data;
    private final int cols;
    private Matrix<E> transposed;

    public ArrayMatrix(int rows, int cols) {
        this.data = new Object[rows][];
        this.cols = cols;
        for (int i = 0; i < data.length; i++) {
            data[i] = new Object[cols];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = null;
            }
        }
    }

    @Override
    public E get(int i, int j) {
        if (i < 0 || i >= data.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid row index: " + i + " for row count " + data.length);
        }
        if (j < 0 || j >= cols) {
            throw new ArrayIndexOutOfBoundsException("Invalid col index: " + j + " for column count " + cols);
        }
        //noinspection unchecked
        return (E) data[i][j];
    }

    @Override
    public void set(int i, int j, E value) {
        data[i][j] = value;
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override
    public int rows() {
        return data.length;
    }

    @Override
    public Matrix<E> transpose() {
        if (transposed == null) {
            transposed = new TransposedMatrix<>(this);
        }
        return transposed;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ArrayMatrix that = (ArrayMatrix) o;
        return cols == that.cols && Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(cols);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return MatrixPrinter.getInstance().toString(this);
    }

}
