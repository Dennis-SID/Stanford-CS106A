package com.shpp.p2p.cs.dsidelnik.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment2 Part3
 * Draw a two pawprints on a screen
 */
public class Assignment2Part3 extends WindowProgram {

    /**
     * Constants that regulates a size of the window
     */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    /**
     * Constants to control situation and size of the paw prints on a screen
     */
    private static final double FIRST_TOE_OFFSET_X = 20;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 40;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /**
     * the position of the heel relative to the upper-left corner of the paw print
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /**
     * each toe is an oval with this width and height
     */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /**
     * the heel is an oval with this width and height
     */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a paw print. The parameters should specify the upper-left corner of the
     * bounding box containing that paw print
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for
     *          the paw print
     * @param y The y coordinate of the upper-left corner of the bounding box for
     *          the paw print
     */
    private void drawPawprint(double x, double y) {
        drawHeel(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y);
        drawToe(x + HEEL_OFFSET_X - FIRST_TOE_OFFSET_X, y + HEEL_OFFSET_Y - FIRST_TOE_OFFSET_Y);
        drawToe(x + SECOND_TOE_OFFSET_X, y + HEEL_OFFSET_Y - SECOND_TOE_OFFSET_Y);
        drawToe(x + THIRD_TOE_OFFSET_X, y + HEEL_OFFSET_Y - THIRD_TOE_OFFSET_Y);
    }

    /**
     * Draws heel without a toes
     *
     * @param x sets position of the heel on a window by x coordinate
     * @param y sets position of the heel on a window by y coordinate
     */
    private void drawHeel(double x, double y) {
        GOval heel = new GOval(x, y, HEEL_WIDTH, HEEL_HEIGHT);
        heel.setFilled(true);
        heel.setFillColor(Color.BLACK);
        heel.setColor(Color.BLACK);
        add(heel);
    }

    /**
     * Draws toe
     *
     * @param x sets position of the toe on a window by x coordinate
     * @param y sets position of the toe on a window by y coordinate
     */
    private void drawToe(double x, double y) {
        GOval toe = new GOval(x, y, TOE_WIDTH, TOE_HEIGHT);
        toe.setFilled(true);
        toe.setFillColor(Color.BLACK);
        toe.setColor(Color.BLACK);
        add(toe);
    }
}
