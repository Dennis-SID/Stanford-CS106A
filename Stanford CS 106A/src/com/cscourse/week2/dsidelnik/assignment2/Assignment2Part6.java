package com.shpp.p2p.cs.dsidelnik.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment2 Part6
 * Draw caterpillar
 */
public class Assignment2Part6 extends WindowProgram {
    /*
     * constants for establishing program window size
     */
    public static final int APPLICATION_WIDTH = 685;
    public static final int APPLICATION_HEIGHT = 285;

    /*
     * constants to define diameter of the circle and offsets
     * by x and y coordinate axis
     */
    public static final int CIRCLE_DIAMETER = 180;
    public static final int CIRCLE_OFFSET_X = 100;
    public static final int CIRCLE_OFFSET_Y = 80;

    /*
     * constants which regulates size of caterpillar
     */
    public static final int CATERPILLAR_SIZE = 3;


    /**
     * main action method
     */
    public void run() {
        drawCaterpillar();
    }


    public void drawCaterpillar() {
        int offset = 0;
        for (int i = 0; i < CATERPILLAR_SIZE; i++) {
            drawCircle(offset, CIRCLE_OFFSET_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            offset += CIRCLE_OFFSET_X;
            drawCircle(offset, 0, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            offset += CIRCLE_OFFSET_X;
        }
    }

    /**
     * Draws circle or oval
     *
     * @param x      starting point of drawing oval on x axis
     * @param y      starting point of drawing oval on y axis
     * @param width  sets width of a circle or oval
     * @param height sets height of a circle or oval
     */
    public void drawCircle(double x, double y, double width, double height) {
        GOval circle = new GOval(x, y, width, height);
        circle.setFilled(true);
        circle.setFillColor(Color.GREEN);
        circle.setColor(Color.RED);
        add(circle);
    }
}
