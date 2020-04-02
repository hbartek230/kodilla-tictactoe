package com.kodilla;

public enum ImageType {

    CIRCLE("file:src/resources/circle.png"), CROSS("file:src/resources/cross.png"), EMPTY("file:src/resources/empty.png");

    private String imageAdress;

    ImageType(String imageAdress) {
        this.imageAdress = imageAdress;
    }

    String getImageAdress(){
        return this.imageAdress;
    }
}
