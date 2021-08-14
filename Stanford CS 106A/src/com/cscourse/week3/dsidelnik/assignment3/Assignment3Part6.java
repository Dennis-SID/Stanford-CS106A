package com.cscourse.week3.dsidelnik.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part6 extends WindowProgram {

    private static final Color CIRCLE_ONE_COLOR = Color.BLACK;
    private static final Color CIRCLE_TWO_COLOR = Color.WHITE;

    private static final double BALL_SIZE = 30.0;

    public void run() {
        ball();
    }


    Runnable blackCircle = () -> {drawBlackCircle(Color.BLACK);};
    Runnable whiteCircle = () -> {drawBlackCircle(Color.WHITE);};

    private void drawBlackCircle(Color color) {
        double radius = 0.0;
        GOval circle = new GOval(radius, radius);
        circle.setFilled(true);
        circle.setFillColor(color);
        circle.setColor(color);

        while(true) {
            radius = 0.0;
            circle.setSize(radius, radius);
            while(radius <= getWidth() * 2 && radius <= getHeight() * 2) {
                circle.setSize(radius, radius);
                add(circle, getWidth() / 2.0 - radius / 2, getHeight() / 2.0 - radius / 2);
                radius += 10;
                pause(50);
            }
        }
    }

    private void ball() {
        GOval ball = new GOval(10,10);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        add(ball, 10, 10);
        double directionX = 1;
        double directionY = 1;
        while(true) {
            if (ball.getY() + 10 > getHeight()) {
                directionY = -directionY;
            } else if (ball.getY() < 0) {
                directionY = 1;
            } else if (ball.getX() + 10 >= getWidth()) {
                directionX = -directionX;
            } else if (ball.getX() <= 0) {
                directionX = 1;
            }
            ball.move(directionX, directionY);
            pause(10);
        }
    }

}
