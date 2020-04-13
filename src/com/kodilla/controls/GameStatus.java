package com.kodilla.controls;

import java.util.*;

public class GameStatus {

    private List<Figure> gameBoard;

    public GameStatus() {
        this.gameBoard = new ArrayList<>();
    }

    public void addFigure(Figure figure) {
        gameBoard.add(figure);
    }

    public void mouseClicked(String figureType, int rowClicked, int columnClicked) {
        int index = findIndexOfClickedElement(rowClicked, columnClicked);
        setNewElementOnBoard(index, rowClicked, columnClicked, figureType);
    }

    private int findIndexOfClickedElement(int rowClicked, int columnClicked) {
        Figure figure = gameBoard.stream()
                .filter(r -> r.getRowNumber() == rowClicked)
                .filter(r -> r.getColNumber() == columnClicked)
                .findAny()
                .orElse(null);

        return gameBoard.indexOf(figure);
    }

    private void setNewElementOnBoard(int index, int rowClicked, int columnClicked, String figureType) {
        Figure newFigure = new Figure(rowClicked, columnClicked, Figure.FigureType.valueOf(figureType));
        gameBoard.set(index, newFigure);
    }
}
