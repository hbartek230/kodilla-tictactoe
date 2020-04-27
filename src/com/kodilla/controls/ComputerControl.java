package com.kodilla.controls;

import java.util.List;
import java.util.Random;

public class ComputerControl {

    private final static String computerMark = "CIRCLE";

    public FieldState selectComputerFigure(List<FieldState> emptyFieldStates) {
        FieldState computerField = emptyFieldStates.get(new Random().nextInt(emptyFieldStates.size()));

        return new FieldState(computerField.getRowNumber(), computerField.getColNumber(),
                FigureType.valueOf(computerMark));
    }

}
