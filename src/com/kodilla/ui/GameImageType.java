package com.kodilla.ui;

import com.kodilla.controls.FigureType;

public enum GameImageType {

    CIRCLE("file:src/resources/circle.png"),
    CROSS("file:src/resources/cross.png"),
    EMPTY("file:src/resources/empty.png");

    private String imageAdress;

    GameImageType(String imageAdress) {
        this.imageAdress = imageAdress;
    }

    String getImageAdress() {
        return this.imageAdress;
    }

    static GameImageType fromFigureType(FigureType type) {
        GameImageType returnType = null;
        switch (type){
            case EMPTY: {
                returnType = GameImageType.EMPTY;
                break;
            }
            case CIRCLE: {
                returnType = GameImageType.CIRCLE;
                break;
            }
            case CROSS: {
                returnType = GameImageType.CROSS;
                break;
            }
        }
        return returnType;
    }
}
