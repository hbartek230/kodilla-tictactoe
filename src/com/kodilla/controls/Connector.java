package com.kodilla.controls;

import java.util.List;

public interface Connector {

    interface Presenter {
        void userMouseClicked(String userMark, int rowClicker, int columnClicked);

        void setView(com.kodilla.ui.Board view);
    }

    interface Board {
        void fillGameBoard(List<FieldState> gameBoard);
    }

}
