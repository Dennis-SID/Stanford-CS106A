package com.cscourse.week3.dsidelnik.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment3 Part4
 * The program draws a pyramid of bricks
 * number of bricks and it colors can be regulated by the final
 * variables located in the class
 */
public class Assignment3Part4 extends WindowProgram {

    /** Define the height of the brick */
    private static final int BRICK_HEIGHT = 40;

    /** Defines the width of the brick */
    private static final int BRICK_WIDTH = 60;

    /** Define the number of brick in the base level
     *  the same will be number of levels in hte pyramid
     */
    private static final int BRICKS_IN_BASE = 10;

    /**
     * Regulates colors of filling of the bricks and the color of it borders accordingly
     */
    private static final Color BRICK_COLOR = Color.RED;
    private static final Color BRICK_BORDER_COLOR = Color.BLACK;

    /**
     * Main action method that runs the program
     */
    public void run() {
        drawPyramid();
    }

    /**
     * <strong>Draws a whole pyramid</strong>
     * <p>Through two loops using singleBrickMaker method draws pyramid
     * number of bricks defined in final variable BRICKS_IN_BASE
     * depending on number of bricks on base level the pyramid will have the
     * same number of levels</p>
     */
    private void drawPyramid() {
        int bricksNumber = BRICKS_IN_BASE; // gets number of bricks to change it number in loop
        int brickYLocation = getHeight() - BRICK_HEIGHT; // defines starting location of the brick on Y axis
        int baseIndent = ((getWidth() - (BRICKS_IN_BASE * BRICK_WIDTH)) / 2); // define indent to center pyramid
        int brickXLocation = baseIndent; // defines brick location on X axis changed in loop

        for (int level = 1; level <= BRICKS_IN_BASE; level++) {
            for (int brick = 0; brick < bricksNumber; brick++) {
                singleBrickMaker(brickXLocation, brickYLocation);
                brickXLocation += BRICK_WIDTH;
            }
            brickYLocation -= BRICK_HEIGHT; // decreases to draw next level of pyramid
            brickXLocation = baseIndent + ((BRICK_WIDTH / 2) * level);
            bricksNumber--; // decreases number of bricks for next pyramid level
        }
    }

    /**
     * Draws a single brick
     * @param x starting draw location of the brick on X axis
     * @param y starting draw location of the brick on Y axis
     */
    private void singleBrickMaker(double x, double y) {
        GRect rectangle = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        rectangle.setFilled(true);
        rectangle.setFillColor(BRICK_COLOR);
        rectangle.setColor(BRICK_BORDER_COLOR);
        add(rectangle, x, y);
    }
}
