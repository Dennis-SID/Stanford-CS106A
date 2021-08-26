package com.cscourse.week3.dsidelnik.assignment3;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Section3Task3 extends WindowProgram {

    /*
     * variables to regulate colors of objects on a screen
     */
    private static final Color LAND_COLOR = Color.GREEN;
    private static final Color SKY_COLOR = Color.CYAN;
    private static final Color SUN_COLOR = Color.YELLOW;
    private static final double SUN_RADIUS = 30.0;

    /**
     * Main class method
     */
    public void run() {
        drawLandscape();
    }

    private void makeAnimation() {
        drawSun();
        drawLandscape();
    }

    private void drawLandscape() {
        double landY = (getHeight() / 4.0) * 3;
        double landWidth = getWidth();
        double landHeight = getHeight() / 4.0;

        setBackground(SKY_COLOR);
        GRect land = new GRect(landWidth, landHeight);
        land.setFilled(true);
        land.setFillColor(LAND_COLOR);
        land.setColor(LAND_COLOR);
        land.setVisible(true);
        add(land, 0.0, landY);
    }

    private void drawSun() {
        double sunX = getWidth() / 2.0 - SUN_RADIUS / 2.0;
        double sunY = getHeight() / 2.0 - SUN_RADIUS / 2.0;

        GOval sun = new GOval(SUN_RADIUS, SUN_RADIUS);
        sun.setFilled(true);
        sun.setFillColor(SUN_COLOR);
        sun.setVisible(true);
        add(sun, 0.0, 0.0);

        /*return sun;*/
    }


}
