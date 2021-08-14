package week2.dsidelnik.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment2 Part5
 * Visual illusion task
 */
public class Assignment2Part5 extends WindowProgram {
    /*
     * constants for establishing program window size the height = 323 where 23 is
     * height of title bar actual height of working place will be 300
     */
    public static final int APPLICATION_WIDTH = 360;
    public static final int APPLICATION_HEIGHT = 300;

    private static final int UPPER_BOTTOM_BORDER = 20;

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    private static final double STARTING_POINT_X = (float) (APPLICATION_WIDTH / 2) - (((NUM_COLS * BOX_SIZE) + (BOX_SPACING * (NUM_COLS - 1))) / 2);
    private static final double STARTING_POINT_Y = (float) ((APPLICATION_HEIGHT - UPPER_BOTTOM_BORDER) / 2) - (((NUM_ROWS * BOX_SIZE) + (BOX_SPACING * (NUM_ROWS - 1))) / 2);

    /** main action method */
    public void run() {
        drawMatrix();
    }

    /**
     * Draws single square or rectangle
     * @param x starting situation on x coordinate
     * @param y starting situation on y coordinate
     */
    private void drawSquare(double x, double y) {
        GRect square = new GRect(x, y, Assignment2Part5.BOX_SIZE, Assignment2Part5.BOX_SIZE);
        square.setFilled(true);
        square.setColor(Color.BLACK);
        square.setFillColor(Color.BLACK);
        add(square);
    }

    /**
     * Builds matrix of squares or rectangles
     * number of rows columns, size of rectangles or squares
     * sets by a static class constants
     */
    private void drawMatrix() {
        double paddingBetweenRows = STARTING_POINT_Y;
        for (int i = 0; i < NUM_ROWS; i++) {
            double paddingBetweenColumns = STARTING_POINT_X;
            for (int j = 0; j < NUM_COLS; j++) {
                drawSquare(paddingBetweenColumns, paddingBetweenRows
                );
                paddingBetweenColumns += BOX_SPACING + BOX_SIZE;
            }
            paddingBetweenRows += BOX_SPACING + BOX_SIZE;
        }
    }
}
