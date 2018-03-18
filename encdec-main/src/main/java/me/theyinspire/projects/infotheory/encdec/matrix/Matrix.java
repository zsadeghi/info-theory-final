package me.theyinspire.projects.infotheory.encdec.matrix;

public interface Matrix<E> {

    E get(int i, int j);

    void set(int i, int j, E value);

    int cols();

    int rows();

    Matrix<E> transpose();

    default Matrix<E> combine(Matrix<E> that) {
        if (this.rows() != that.rows()) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible");
        }
        return new CombinedMatrix<>(this, that);
    }

}
