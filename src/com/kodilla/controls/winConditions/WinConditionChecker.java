package com.kodilla.controls.winConditions;

import com.kodilla.controls.FieldState;

import java.util.List;

public interface WinConditionChecker {

    boolean checkIfWin(List<FieldState> actualGameBoard, int rowClicked, int columnClicked, String gameMark);

}
