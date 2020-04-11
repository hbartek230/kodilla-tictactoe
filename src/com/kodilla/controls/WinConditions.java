package com.kodilla.controls;

import com.kodilla.ui.FieldView;
import com.kodilla.ui.ImageType;

import java.util.ArrayList;
import java.util.Arrays;

public class WinConditions {

    private ArrayList<ImageType> winningFields;
    private boolean win;

    public WinConditions(ImageType... types) {
        this.winningFields = new ArrayList<>(Arrays.asList(types));
    }

    public ArrayList<ImageType> getWinningFields() {
        return winningFields;
    }

    public boolean ifWin(ImageType type) {
        if (winningFields.get(0) != null && winningFields.get(1) != null && winningFields.get(2) != null)
            this.win = ((this.winningFields.get(0).equals(this.winningFields.get(1)) &&
                    this.winningFields.get(1).equals(this.winningFields.get(2))) && this.winningFields.get(0).equals(type));
        System.out.println("If Win: " + win);
        return win;
    }
}
