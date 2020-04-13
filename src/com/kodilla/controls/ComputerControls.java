package com.kodilla.controls;

import com.kodilla.ui.Board;

import java.util.List;
import java.util.Random;

public class ComputerControls {

    public void setComputerFigure(List<Figure> emptyFigures, List<Figure> allFigures) {
        Figure newFigure = emptyFigures.get(new Random().nextInt(emptyFigures.size()));
        int index = allFigures.indexOf(newFigure);

        Board.setFigureAfterComputerMove(newFigure.getRowNumber(), newFigure.getColNumber(), index);
    }

}
