package week2.dsidelnik.assignment2;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment2 Part4
 * Implements program that draws three color flag
 */
public class Assignment2Part4 extends WindowProgram {
    /**
     * constants that defines size of the window
     */
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 800;

    /**
     * sets the borders of the window
     */
    private static final int LEFT_RIGHT_BORDERS = 180;
    private static final int UPPER_BOTTOM_BORDERS = 70;
    private static final int PADDING = 20;

    /**
     * constants to set flag width and height depending on program window width and height
     */
    private static final int FLAG_WIDTH = APPLICATION_WIDTH - (LEFT_RIGHT_BORDERS * 2);
    private static final int FLAG_HEIGHT = APPLICATION_HEIGHT - (UPPER_BOTTOM_BORDERS * 2) - PADDING;
    private static final int FLAG_COLOR_WIDTH = FLAG_WIDTH / 3;

    /**
     * Main action method that starts a program
     */
    public void run() {
        drawFlag();
    }

    /**
     * Draws flag on a program window
     *
     */
    private void drawFlag() {
        drawFlagColor(Assignment2Part4.LEFT_RIGHT_BORDERS, Color.BLACK);
        drawFlagColor((double) Assignment2Part4.LEFT_RIGHT_BORDERS + FLAG_COLOR_WIDTH, Color.YELLOW);
        drawFlagColor((double) Assignment2Part4.LEFT_RIGHT_BORDERS + FLAG_COLOR_WIDTH + FLAG_COLOR_WIDTH, Color.RED);
        printText();
    }

    /**
     * Draws color of the flag
     *  @param x     define situation on a x coordinate axis
     * @param color sets color
     */
    private void drawFlagColor(double x, Color color) {
        GRect rectangle = new GRect(x, Assignment2Part4.UPPER_BOTTOM_BORDERS, FLAG_COLOR_WIDTH, FLAG_HEIGHT);
        rectangle.setFilled(true);
        rectangle.setColor(color);
        rectangle.setFillColor(color);
        add(rectangle);
    }

    /**
     * Prints text on a screen
     *
     */
    private void printText() {
        GLabel label = new GLabel("Flag of Belgium");
        label.setFont("Verdana-50");
        label.setColor(Color.BLACK);
        add(label, getWidth() - label.getWidth(), getHeight() - 10);
    }
}
