package me.theyinspire.projects.infotheory.encdec.matrix;

public class CombinedMatrix<E> implements Matrix<E> {

    private final Matrix<E> first;
    private final Matrix<E> second;
    private Matrix<E> transposed;

    public CombinedMatrix(final Matrix<E> first, final Matrix<E> second) {
        this.first = first;
        this.second = second;
    }

    private Matrix<E> matrix(int i, int j) {
        return j < first.cols() ? first : second;
    }

    private int col(int col) {
        return col < first.cols() ? col : col - first.cols();
    }

    @Override
    public E get(final int i, final int j) {
        return matrix(i, j).get(i, col(j));
    }

    @Override
    public void set(final int i, final int j, final E value) {
        matrix(i, j).set(i, col(j), value);
    }

    @Override
    public int cols() {
        return first.cols() + second.cols();
    }

    @Override
    public int rows() {
        return first.rows();
    }

    @Override
    public Matrix<E> transpose() {
        if (transposed == null) {
            transposed = new TransposedMatrix<>(this);
        }
        return transposed;
    }

    @Override
    public String toString() {
        return MatrixPrinter.getInstance().toString(this);
    }

}
