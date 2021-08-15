package com.cscourse.week3.dsidelnik.assignment3;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/*
ANIMATION
bouncing balls
Idea: there are fixed quantity of balls on a screen with two different colors
while they bounce to each other one ball gives color to another ball
 */
public class Assignment3Part6 extends WindowProgram {

    private static final Color CIRCLE_ONE_COLOR = Color.BLACK;
    private static final Color CIRCLE_TWO_COLOR = Color.WHITE;

    private static final double WINDOW_BORDER_WIDTH = 5.0;
    private static final int BALLS_NUMBER = 10;
    private static final double FRAMES_SECOND = 10.0;

    private static final double BALL_SIZE = 50.0;

    public void run() {

        drawAllBorders();
        for (int i = 0; i < BALLS_NUMBER; i++) {
            new Thread(ballAnimation).start();
        }
        double startTime = System.nanoTime() / 1000000000.0;
        System.out.println("START TIME" + startTime);
        while(true) {
            if (( (System.nanoTime() / 1000000000) - startTime ) >= 5.0 ) System.exit(0);
            System.out.println("Current TIME" + ((System.nanoTime() / 1000000000) - startTime));
        }

    }


    Runnable blackCircle = () -> {
        drawBlackCircle(Color.BLACK);
    };
    Runnable whiteCircle = () -> {
        drawBlackCircle(Color.WHITE);
    };
    Runnable ballAnimation = this::ball;

    private void drawBlackCircle(Color color) {
        double radius = 0.0;
        GOval circle = new GOval(radius, radius);
        circle.setFilled(true);
        circle.setFillColor(color);
        circle.setColor(color);

        while (true) {
            radius = 0.0;
            circle.setSize(radius, radius);
            while (radius <= getWidth() * 2 && radius <= getHeight() * 2) {
                circle.setSize(radius, radius);
                add(circle, getWidth() / 2.0 - radius / 2, getHeight() / 2.0 - radius / 2);
                radius += 10;
                pause(50);
            }
        }
    }

    private void ball() {
        RandomGenerator random = RandomGenerator.getInstance();
        GOval ball = new GOval(BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setFillColor(random.nextColor());

        add(ball, random.nextDouble(BALL_SIZE, getWidth() - BALL_SIZE),
                random.nextDouble(BALL_SIZE, getHeight() - BALL_SIZE));
        double directionX = random.nextDouble(1.0, 3.0);
        double directionY = random.nextDouble(1.0, 3.0);
        double secondInMinute = 60.0;
        double ballX = ball.getX();
        double ballY = ball.getY();


        while (true) {
            if (ball.getY() + BALL_SIZE > getHeight()) {
                directionY = -directionY;
                ball.setFillColor(Color.GREEN);
            } else if (ball.getY() < 0) {
                directionY = random.nextDouble(1.0, 3.0);
                ball.setFillColor(Color.RED);
            } else if (ball.getX() + BALL_SIZE >= getWidth()) {
                directionX = -directionX;
                ball.setFillColor(Color.YELLOW);
            } else if (ball.getX() <= 0) {
                directionX = random.nextDouble(1.0, 3.0);
                ball.setFillColor(Color.BLUE);
            }

            /*if (getElementAt(ballX, ballY) != null) {
                directionY = 1;
            } else if (getElementAt(ballX, ballY + BALL_SIZE) != null) {
                directionX = 1;
            } else if (getElementAt(ballX + BALL_SIZE, ballY) != null) {
                directionX = -1;
            } else if (getElementAt(ballX + BALL_SIZE, ballY + BALL_SIZE) != null) {
                directionY = -1;
            }*/


            ball.move(directionX, directionY);
            pause(secondInMinute / FRAMES_SECOND);
        }
    }

    private double randomDirection() {
        Random random = new Random();
        return random.nextInt(2) == 1 ? 1 : -1;
    }

    private void drawOneBorder(double x, double y, double width, double height, Color color) {
        GRect border = new GRect(x, y, width, height);
        border.setFilled(true);
        border.setFillColor(color);
        border.setColor(color);
        border.setVisible(true);
        add(border);
    }

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
