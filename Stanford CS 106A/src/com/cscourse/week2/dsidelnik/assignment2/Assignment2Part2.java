package week2.dsidelnik.assignment2;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment2 Part2
 * The task is to create a white rectangle which lays on a 4 black circles
 */
public class Assignment2Part2 extends WindowProgram {

    /**
     * Final constants that defines width and height of the application
     */
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    /**
     * Main action method that runs program
     */
    public void run() {
        draw();
    }

    /**
     * Draws rectangle and circles into specific place on a screen
     */
    private void draw() {

        double appWidth = getWidth();
        double appHeight = getHeight();
        double cirDiameter = appWidth / 4;
        double cirRadius = cirDiameter / 2;

        drawCircle(0, 0, cirDiameter, cirDiameter);
        drawCircle(0, appHeight - cirDiameter, cirDiameter, cirDiameter);
        drawCircle(appWidth - cirDiameter, 0, cirDiameter, cirDiameter);
        drawCircle(appWidth - cirDiameter, appHeight - cirDiameter, cirDiameter,
                cirDiameter);

        drawSquare(cirRadius, cirRadius, appWidth - cirDiameter, appHeight - cirDiameter);
    }

    /**
     * Draws a circle on a screen
     *
     * @param x      coordinate axis
     * @param y      coordinate axis
     * @param width  of the circle
     * @param height of the circle
     */
    private void drawCircle(double x, double y, double width, double height) {
        GOval gCircle = new GOval(x, y, width, height);
        gCircle.setFilled(true);
        gCircle.setFillColor(Color.BLACK);
        add(gCircle);
    }

    /**
     * Draws square on a screen
     *
     * @param x      on coordinate axis
     * @param y      on coordinate axis
     * @param width  defines width of the rectangle
     * @param height defines height of the rectangle
     */
    private void drawSquare(double x, double y, double width, double height) {
        GRect square = new GRect(x, y, width, height);
        square.setFilled(true);
        square.setFillColor(Color.WHITE);
        square.setColor(Color.WHITE);
        add(square);
    }
}
