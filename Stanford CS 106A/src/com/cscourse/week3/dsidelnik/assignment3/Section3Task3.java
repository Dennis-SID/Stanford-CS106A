package com.cscourse.week3.dsidelnik.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Section3 Task3
 * Makes animation of the sun set
 */
public class Section3Task3 extends WindowProgram {

    /*
     * variables to regulate colors of objects on a screen
     */
    private static final Color LAND_COLOR = Color.GREEN;
    private static final Color SKY_COLOR = Color.CYAN;
    private static final Color SUN_COLOR = Color.YELLOW;
    private static final double SUN_RADIUS = 30.0;
    private static final double SUN_SPEED = 1.0;
    private static final double PAUSE_TIME = 10.0;

    /**
     * Main class method
     */
    public void run() {
        makeAnimation();

    }

    /*
     * Uses all helper methods. Provides animation logic
     */
    private void makeAnimation() {
        GOval ball = drawSun();
        drawLandscape();

        while(ball.getY() < getHeight()) {
            ball.move(0.0, SUN_SPEED);
            pause(PAUSE_TIME);
        }
    }

    /* Draws landscape, for land creates GRect object,
    * to draw a sky just changes background color
    */
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

    /*
     * Draws sun
     * for drawing creates GOval object uses final variables
     */
    private GOval drawSun() {
        double sunX = getWidth() / 2.0 - SUN_RADIUS;
        double sunY = getHeight() / 2.0 - SUN_RADIUS;

        GOval sun = new GOval(SUN_RADIUS * 2.0, SUN_RADIUS * 2.0);
        sun.setFilled(true);
        sun.setFillColor(SUN_COLOR);
        sun.setVisible(true);
        add(sun, sunX, sunY);

        return sun;
    }

}
