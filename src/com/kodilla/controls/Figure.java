package com.kodilla.controls;

import java.util.ArrayList;
import java.util.List;

public final class Figure {
    private final int rowNumber;
    private final int colNumber;
    private final FigureType type;

    public enum FigureType {
        EMPTY, CIRCLE, CROSS
    }

    public Figure(final int rowNumber, final int colNumber, final FigureType type) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.type = type;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public FigureType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return rowNumber == figure.rowNumber &&
                colNumber == figure.colNumber &&
                type == figure.type;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "rowNumber=" + rowNumber +
                ", colNumber=" + colNumber +
                ", type=" + type +
                '}';
    }
}
