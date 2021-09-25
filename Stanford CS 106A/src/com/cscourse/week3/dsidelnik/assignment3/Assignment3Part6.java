package com.cscourse.week3.dsidelnik.assignment3;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * <b>Assignment3 Part6</b>
 * <p>Program that draws animation keep track of time
 * and terminate it implementation in established time
 * implementation time as other parameters of the animation can
 * be changed through adjusting code final variables</p>
 */
public class Assignment3Part6 extends WindowProgram {

    // used to set width of the borders in the window
    private static final double WINDOW_BORDER_WIDTH = 5.0;

    private static final int BALLS_NUMBER = 10;
    private static final double FRAMES_SECOND = 10.0;

    private static final double BALL_SIZE = 50.0;

    // sets duration of the animation in milliseconds
    private static final double ANIMATION_DURATION = 7000;

    // Runnable interface used to create and start threads in run method
    Runnable ballAnimation = this::ball;

    /**
     * Main action method that starts animation
     * <p>Invokes drawAllBorder method then creates balls (separate thread for each one)
     * number of balls and other connected parameters can be set in final variables
     * keep track of time duration of the animation and after established time terminates the
     * program implementation</p>
     * <b>Uses <code>System.exit()</code> method to stop the program</b>
     */
    public void run() {

        drawAllBorders();

        for (int i = 0; i < BALLS_NUMBER; i++) {
            new Thread(ballAnimation).start();
        }

        long startTime = System.currentTimeMillis();
        long timeElapsed = 0;
        while (timeElapsed <= ANIMATION_DURATION) {
            long currTime = System.currentTimeMillis();
            timeElapsed = currTime - startTime;
            System.out.println("Time elapsed " + timeElapsed); // time logging - used for program control purposes
        }

        /*System.exit(0); // terminates program implementation*/
    }

    /**
     * <b>Creates GObject type object</b>
     * <p>sets it's border color, filled color, starting position
     * on a screen and regulates wall collision behavior according to physics laws
     * in a method number of frames of whole animation is defined
     * number of frames per second can be changed in final variable <code>FRAMES_SECOND</code></p>
     */
    private void ball() {
        RandomGenerator random = RandomGenerator.getInstance();
        double minBallSpeed = 1.0;
        double maxBallSpeed = 3.0;

        GOval ball = new GOval(BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setFillColor(random.nextColor());

        add(ball, random.nextDouble(BALL_SIZE, getWidth() - BALL_SIZE),
                random.nextDouble(BALL_SIZE, getHeight() - BALL_SIZE));
        double directionX = random.nextDouble(minBallSpeed, maxBallSpeed);
        double directionY = random.nextDouble(minBallSpeed, maxBallSpeed);
        double secondInMinute = 60.0; // used to avoid magic numbers and bring more clarity into code

        while (true) {
            if (ball.getY() + BALL_SIZE > getHeight()) {
                directionY = -directionY;
                ball.setFillColor(Color.GREEN);
            } else if (ball.getY() < 0) {
                directionY = random.nextDouble(minBallSpeed, maxBallSpeed);
                ball.setFillColor(Color.RED);
            } else if (ball.getX() + BALL_SIZE >= getWidth()) {
                directionX = -directionX;
                ball.setFillColor(Color.YELLOW);
            } else if (ball.getX() <= 0) {
                directionX = random.nextDouble(minBallSpeed, maxBallSpeed);
                ball.setFillColor(Color.BLUE);
            }

            ball.move(directionX, directionY);
            pause(secondInMinute / FRAMES_SECOND);
        }
    }

    /**
     * Draws a line which serves as a border of the screen
     * used to draw borders on each side of the screen with particular width and color
     *
     * @param x      starting draw position an X coordinate axis
     * @param y      starting draw position on Y coordinate axis
     * @param width  sets width of the line
     * @param height sets height of the line
     * @param color  sets color of the line
     */
    private void drawOneBorder(double x, double y, double width, double height, Color color) {
        GRect border = new GRect(x, y, width, height);
        border.setFilled(true);
        border.setFillColor(color);
        border.setColor(color);
        border.setVisible(true);
        add(border);
    }

    /**
     * <p>Using <code>drawOneBorder()</code> method draws fours borders
     * on each side of the window</p>
     * <p>Colors of the borders particularly defined in the method and can be changed there</p>
     */
    private void drawAllBorders() {

        // draw bottom border
        drawOneBorder(0.0, getHeight() - WINDOW_BORDER_WIDTH, getWidth(), WINDOW_BORDER_WIDTH, Color.GREEN);

        //draw upper border
        drawOneBorder(0.0, 0.0, getWidth(), WINDOW_BORDER_WIDTH, Color.RED);

        // draw left border
        drawOneBorder(0.0, WINDOW_BORDER_WIDTH, WINDOW_BORDER_WIDTH, getHeight() - WINDOW_BORDER_WIDTH * 2, Color.BLUE);

        // draw right border
        drawOneBorder(getWidth() - WINDOW_BORDER_WIDTH, WINDOW_BORDER_WIDTH, WINDOW_BORDER_WIDTH,
                getHeight() - WINDOW_BORDER_WIDTH * 2, Color.YELLOW);
    }
}
