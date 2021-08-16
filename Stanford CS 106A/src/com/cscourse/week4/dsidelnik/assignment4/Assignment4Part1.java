package com.cscourse.week4.dsidelnik.assignment4;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment4Part1 extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;


    public void run() {
        endGameWin();
        /*for (int i = 0; i < NTURNS; i++) {
            playGame();
        }*/
    }

    private void playGame() {
        GRect paddle = drawPaddle();
        GOval ball = drawBall();
        drawAllBrickLevels();
    }

    private void drawBrick(double x, double y, Color color) {
        GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        brick.setLocation(x, y);
        brick.setFilled(true);
        brick.setFillColor(color);
        brick.setVisible(true);
        add(brick);
    }

    private void drawBrickRow(double x, double y, Color color) {
        double brickX = x;
        for (int i = 0; i < NBRICKS_PER_ROW; i++) {
            drawBrick(brickX, y, color);
            brickX += BRICK_WIDTH + BRICK_SEP;
        }

    }

    private void drawAllBrickLevels() {
        double brickY = BRICK_Y_OFFSET;
        Color color;
        for (int i = 0; i < NBRICK_ROWS; i++) {
            color = defineColor(i);
            drawBrickRow(0.0, brickY, color);
            brickY += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    private GRect drawPaddle() {
        GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        double paddleX = (getWidth() / 2.0) - PADDLE_WIDTH / 2.0;
        double paddleY = getHeight() - PADDLE_Y_OFFSET;
        paddle.setLocation(paddleX, paddleY);
        paddle.setVisible(true);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        add(paddle);
        return paddle;
    }

    private GOval drawBall() {
        double ballX = getWidth() / 2.0 - BALL_RADIUS / 2.0;
        double ballY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BRICK_SEP;
        GOval ball = new GOval(BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        ball.setVisible(true);
        ball.setLocation(ballX, ballY);
        add(ball);

        return ball;
    }


    private Color defineColor(int rowNumber) {
        Color outputColor;

        switch (rowNumber) {
            case (0) :
            case (1) : outputColor = Color.RED;
            break;
            case (2) :
            case (3) : outputColor = Color.ORANGE;
            break;
            case (4) :
            case (5) : outputColor = Color.YELLOW;
            break;
            case (6) :
            case (7) : outputColor = Color.GREEN;
            break;
            case (8) :
            case (9) : outputColor = Color.CYAN;
            break;
            default : outputColor = Color.WHITE;
        }
        return outputColor;
    }

    private void endGameWin() {
        int fontSize = 60;
        int animationFrame = 20;

        GLabel winLabel = new GLabel("VICTORY!!!");
        winLabel.setVisible(true);
        winLabel.setColor(Color.RED);


        for (int i = 0; i < fontSize; i += 3) {
            double labelX = getWidth() / 2.0 - winLabel.getWidth() / 2.0;
            double labelY = getHeight() / 2.0 - winLabel.getHeight() / 2.0;
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, i);
            winLabel.setFont(font);
            winLabel.setLocation(labelX, labelY);
            add(winLabel);
            pause(animationFrame);
        }










    }

 }
