package com.cscourse.week12.dsidelnik.assignment12;

public class Pixel {

    private int coordX;
    private int coordY;


    public Pixel() {}

    public Pixel(int x, int y) {
        coordX = x;
        coordY = y;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordX (int x) {
        coordX = x;
    }

    public void setCoordY(int y) {
        coordY = y;
    }
}
