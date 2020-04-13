package com.kodilla.controls;

import com.kodilla.ui.Board;

import java.util.List;
import java.util.Random;

public class ComputerControls {

    public void setComputerFigure(List<FieldState> emptyFieldStates, List<FieldState> allFieldStates) {
        FieldState newFieldState = emptyFieldStates.get(new Random().nextInt(emptyFieldStates.size()));
        int index = allFieldStates.indexOf(newFieldState);

        //Board.setFigureAfterComputerMove(newFieldState.getRowNumber(), newFieldState.getColNumber(), index);
    }

}
