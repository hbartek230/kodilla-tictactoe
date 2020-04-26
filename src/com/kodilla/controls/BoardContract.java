package com.kodilla.controls;

import java.util.List;

public interface BoardContract {

    interface Presenter {
        void setView(View view);
    }

    interface View {
        void fillGameBoard(List<FieldState> gameBoard);

        void showWinMessage(String winner);

        void showInfoAboutUserMove();

        void showInfoAboutComputerMove();
    }

}
