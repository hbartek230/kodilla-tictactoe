package com.kodilla.controls;

import java.util.Objects;

public final class FieldState {
    private final int rowNumber;
    private final int colNumber;
    private final FigureType type;

    public FieldState(final int rowNumber, final int colNumber, final FigureType type) {
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
        FieldState fieldState = (FieldState) o;
        return rowNumber == fieldState.rowNumber &&
                colNumber == fieldState.colNumber &&
                type == fieldState.type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
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
