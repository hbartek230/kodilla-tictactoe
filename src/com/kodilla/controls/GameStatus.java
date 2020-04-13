package com.kodilla.controls;

import java.util.*;
import java.util.stream.Collectors;

public class GameStatus {

    private List<List<Figure>> gameBoard;

    public GameStatus() {
        this.gameBoard = new ArrayList<>();
    }

    public void addFigure(Figure figure) {
        gameBoard.add(new ArrayList<>(Collections.singletonList(figure)));
    }

    public void mouseClicked(String figureType, int rowClicked, int columnClicked) {
        int index = findIndexOfClickedElement(rowClicked, columnClicked);
        setNewElementOnBoard(index, rowClicked, columnClicked, figureType);
    }

    private int findIndexOfClickedElement(int rowClicked, int columnClicked) {
        List<Figure> figure = gameBoard.stream()
                .flatMap(Collection::stream)
                .filter(r -> r.getRowNumber() == rowClicked)
                .filter(r -> r.getColNumber() == columnClicked)
                .collect(Collectors.toList());

        return gameBoard.indexOf(figure);
    }

    private void setNewElementOnBoard(int index, int rowClicked, int columnClicked, String figureType) {
        Figure newFigure = new Figure(rowClicked, columnClicked, Figure.FigureType.valueOf(figureType));
        gameBoard.set(index, new ArrayList<>(Collections.singletonList(newFigure)));
    }
}
