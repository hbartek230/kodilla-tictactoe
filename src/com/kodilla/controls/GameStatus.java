package com.kodilla.controls;

import com.kodilla.ui.FieldView;

import java.util.*;
import java.util.stream.Collectors;

public class GameStatus {

    private List<Figure> gameBoard;
    private ComputerControls computerControl;
    private Figure.FigureType emptyType = Figure.FigureType.EMPTY;

    public GameStatus() {
        this.gameBoard = new ArrayList<>();
    }

    public void addFigure(Figure figure) {
        gameBoard.add(figure);
    }

    public void mouseClicked(String figureType, int rowClicked, int columnClicked) {
        computerControl = new ComputerControls();
        int index = findIndexOfClickedElement(rowClicked, columnClicked);
        setNewElementOnBoard(index, rowClicked, columnClicked, figureType);
        makeComputerMove();
    }

    private void makeComputerMove() {
        List<Figure> emptyFigures = gameBoard.stream()
                .filter(r -> r.getType() == emptyType)
                .collect(Collectors.toList());

        if (emptyFigures.size() > 1) {
            computerControl.setComputerFigure(emptyFigures, gameBoard);
        }
    }

    private int findIndexOfClickedElement(int rowClicked, int columnClicked) {
        Figure figure = gameBoard.stream()
                .filter(r -> r.getRowNumber() == rowClicked && r.getColNumber() == columnClicked)
                .findAny()
                .orElse(null);

        return gameBoard.indexOf(figure);
    }

    public void setNewElementOnBoard(int index, int rowClicked, int columnClicked, String figureType) {
        Figure newFigure = new Figure(rowClicked, columnClicked, Figure.FigureType.valueOf(figureType));
        gameBoard.set(index, newFigure);
    }
}
