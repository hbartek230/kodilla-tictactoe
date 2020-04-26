package com.kodilla.controls;

import java.util.List;

public interface BoardContract {

    interface Presenter {
        void setView(View view);

        void selectedFieldByUser(int rowClicked, int columnClicked);

        void restartGame();
    }

    interface View {
        void fillGameBoard(List<FieldState> gameBoard);

        void showWinMessage(String winner);

        void showInfoAboutUserMove();

        void showInfoAboutComputerMove();
    }

}
