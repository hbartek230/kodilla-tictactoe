package com.kodilla.controls;

public class BoardSettings {
    private int rowCount;
    private int columnCount;

    public BoardSettings(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
