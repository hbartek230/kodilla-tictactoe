package com.kodilla.controls;

import java.util.List;
import java.util.Random;

public class ComputerControl {

    public FieldState selectComputerFigure(List<FieldState> emptyFieldStates) {
        return emptyFieldStates.get(new Random().nextInt(emptyFieldStates.size()));
    }

}