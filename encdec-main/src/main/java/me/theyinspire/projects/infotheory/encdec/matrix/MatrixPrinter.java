package me.theyinspire.projects.infotheory.encdec.matrix;

import java.util.stream.IntStream;

public class MatrixPrinter {

    private static final MatrixPrinter INSTANCE = new MatrixPrinter();
    private static final String BORDER = "\u2503";
    private static final String TOP_LEFT = "\u250f";
    private static final String TOP_RIGHT = "\u2513";
    private static final String BOTTOM_LEFT = "\u2517";
    private static final String BOTTOM_RIGHT = "\u251b";

    public static MatrixPrinter getInstance() {
        return INSTANCE;
    }

    public <E> String toString(Matrix<E> matrix) {
        String string = IntStream.range(0, matrix.rows())
                                       .mapToObj(row -> BORDER + " " + IntStream.range(0, matrix.cols())
                                                                                .mapToObj(col -> matrix.get(row, col))
                                                                                .map(String::valueOf)
                                                                                .reduce((left, right)
                                                                                                -> left + " " + right)
                                                                                .orElse("") + " " + BORDER)
                                       .reduce((left, right) -> left + "\n" + right)
                                       .orElse("");
        if (string.contains("\n")) {
            string = string.replaceFirst(BORDER, TOP_LEFT);
            string = string.replaceFirst(BORDER, TOP_RIGHT);
            string = string.replaceFirst(BORDER + "$", BOTTOM_RIGHT);
            string = string.replaceFirst(BORDER + "([^\n]+$)", BOTTOM_LEFT + "$1");
        }
        return string;
    }

}
