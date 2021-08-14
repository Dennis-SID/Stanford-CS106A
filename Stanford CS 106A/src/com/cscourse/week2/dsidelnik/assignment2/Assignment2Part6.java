package week2.dsidelnik.assignment2;

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
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 300;

    /*
     * constants to define diameter of the circle and offsets
     * by x and y coordinate axis
     */
    private static final int CIRCLE_DIAMETER = 200;

    /*
     * constants which regulates size of caterpillar
     */
    private static final int CATERPILLAR_SIZE = 6;

    /* Set caterpillar color */
    private static final Color FILL_COLOR = Color.GREEN;
    private static final Color BORDER_COLOR = Color.RED;


    /**
     * main action method
     */
    public void run() {
        drawCaterpillar();
    }

    /**
     * Draws caterpillar
     */
    private void drawCaterpillar() {
        int offset = 0;
        int circleOffsetY = CIRCLE_DIAMETER / 3;
        int circleOffsetX = CIRCLE_DIAMETER / 2;
        for (int i = 0; i < CATERPILLAR_SIZE; i++) {
            if (i % 2 == 0) {
                drawCircle(offset, circleOffsetY);
                offset += circleOffsetX;
            } else {
                drawCircle(offset, 0);
                offset += circleOffsetX;
            }
        }
    }

    /**
     * Draws circle or oval
     *
     * @param x starting point of drawing oval on x axis
     * @param y starting point of drawing oval on y axis
     */
    private void drawCircle(double x, double y) {
        GOval circle = new GOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        circle.setFilled(true);
        circle.setFillColor(FILL_COLOR);
        circle.setColor(BORDER_COLOR);
        add(circle);
    }
}
