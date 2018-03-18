package me.theyinspire.projects.infotheory.encdec.matrix;

import java.util.Objects;

public class TransposedMatrix<E> implements Matrix<E> {

    private final Matrix<E> original;

    TransposedMatrix(final Matrix<E> original) {
        this.original = original;
    }

    @Override
    public E get(final int i, final int j) {
        return original.get(j, i);
    }

    @Override
    public void set(final int i, final int j, final E value) {
        original.set(j, i, value);
    }

    @Override
    public int cols() {
        return original.rows();
    }

    @Override
    public int rows() {
        return original.cols();
    }

    @Override
    public Matrix<E> transpose() {
        return original;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TransposedMatrix that = (TransposedMatrix) o;
        return Objects.equals(original, that.original);
    }

    @Override
    public int hashCode() {

        return Objects.hash(original);
    }

    @Override
    public String toString() {
        return MatrixPrinter.getInstance().toString(this);
    }

}
