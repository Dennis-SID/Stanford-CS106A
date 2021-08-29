package com.cscourse.week4.dsidelnik.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Assignment4 Part1
 * Implementation of Breakout game
 *
 * @version 1.0
 */
public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_DIAMETER = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Set the pace (speed) of the game
     */
    private static final double GAME_PACE = 3.0;

    /**
     * Sets the pace of the game
     */
    private static final int PAUSE_TIME = 10;

    /**
     * Ball velocity by X and by Y
     */
    private double velocityX;
    private double velocityY;

    /**
     * instance game variables
     */
    private GObject paddle;
    private GObject ball;
    private int brickCounter;

    /**
     * Main action method of the class
     */
    public void run() {
        playGame();

    }

    /**
     * adds mouse listeners
     * depending on the number of turns starts the game
     * if there are no bricks on the window ends the game
     */
    private void playGame() {

        int victory = 1;
        int lose = 0;

        // adds mouse listeners, used method mouseMoved()
        addMouseListeners();

        for (int i = 0; i < NTURNS; i++) {
            setUpGameField();
            gameLogic();

            if (brickCounter == 0) {
                ball.setVisible(false);
                endGame(victory);
                break;
            }

            if (brickCounter > 0) {
                removeAll();
            }

        }
        if (brickCounter > 0) {
            endGame(lose);
        }
    }

    /*
     * Draws all starter objects (bricks, paddle, ball)
     * used at the beginning on each game
     */
    private void setUpGameField() {
        paddle = drawPaddle();
        ball = drawBall();
        drawAllBrickLevels();
        brickCounter = NBRICK_ROWS * NBRICKS_PER_ROW;
    }

    private void gameLogic() {

        velocityGenerator();
        printClickToStart();


        while (true) {
            moveBall();
            if (ball.getY() > getHeight()) {
                break;
            }
            if (brickCounter == 0) {
                break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if ((e.getX() <= getWidth() - PADDLE_WIDTH / 2) && (e.getX() >= PADDLE_WIDTH / 2)) {
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2.0, getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /*
     * Draws a single brick object of GRect type
     * uses final variables to set brick height and width
     * adds object on a screen
     */
    private void drawBrick(double x, double y, Color color) {
        GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        brick.setLocation(x, y);
        brick.setFilled(true);
        brick.setFillColor(color);
        brick.setVisible(true);
        add(brick);
    }

    /*
     * Helper method that draws one row of the bricks
     * used by "drawAllBrickLevels()" method
     */
    private void drawBrickRow(double y, Color color) {
        double brickX = 0.0;
        for (int i = 0; i < NBRICKS_PER_ROW; i++) {
            drawBrick(brickX, y, color);
            brickX += BRICK_WIDTH + BRICK_SEP;
        }

    }

    /*
     * Draws all layers of bricks
     * uses "defineColor()" method to define color depending on the
     * layer of the bricks row
     * number of brick layers can be regulated by class final variables
     */
    private void drawAllBrickLevels() {
        double brickY = BRICK_Y_OFFSET;
        Color color;
        for (int i = 0; i < NBRICK_ROWS; i++) {
            color = defineColor(i);
            drawBrickRow(brickY, color);
            brickY += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    /*
     * Draws object of type GRect
     * Uses final variables to set paddle width and height
     * Adds paddle object on a screen uses final variables to set
     * location of the paddle on the screen
     */
    private GRect drawPaddle() {
        GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        double paddleX = (getWidth() / 2.0) - PADDLE_WIDTH / 2.0;
        double paddleY = getHeight() - PADDLE_Y_OFFSET;
        paddle.setLocation(paddleX, paddleY);
        paddle.setVisible(true);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        paddle.addActionListener(this);
        add(paddle);
        return paddle;
    }

    /*
     * Draws an object of type GOval
     * uses final variables to set radius of the ball
     * adds the ball on the middle of the screen
     */
    private GOval drawBall() {
        double ballX = getWidth() / 2.0 - BALL_DIAMETER / 2.0;
        double ballY = getHeight() / 2.0 - BALL_DIAMETER / 2.0;
        GOval ball = new GOval(BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        ball.setVisible(true);
        ball.setLocation(ballX, ballY);
        add(ball);

        return ball;
    }

    /*
     * Method that makes movement of the ball
     * method also uses the method "getCollidingObject()" to define whether
     * it is colliding with any other objects like brick or paddle
     * if it is than it changes values in instance variables velocityX, velocityY, and brickCounter
     */
    private void moveBall() {
        ball.move(velocityX, velocityY);

        // Check for walls
        if (ball.getX() - velocityX <= 0 && velocityX < 0 || (ball.getX() + velocityX >= (getWidth() - BALL_DIAMETER * 2)
                && velocityX > 0)) {
            velocityX = -velocityX;
        }
        if (ball.getY() - velocityY <= 0 && velocityY < 0) {
            velocityY = -velocityY;
        }
        GObject collider = getCollidingObject();

        // checks if ball collides a paddle it moves up
        if (collider == paddle) {

            // BALL_RADIUS / 2 - 1 needed to avoid slipping of the ball to the paddle
            if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - BALL_DIAMETER * 2 && ball
                    .getY() < getHeight() - PADDLE_Y_OFFSET - BALL_DIAMETER * 2 + (BALL_DIAMETER / 2.0 - 1.0)) {
                velocityY = -velocityY;
            }
        }

        /*
         * checks if game ball collides a brick, it eliminates a brick and change
         * movement course of the ball
         */
        else if (collider != null) {
            remove(collider);
            brickCounter--;
            velocityY = -velocityY;
        }
        pause(PAUSE_TIME);
    }

    /*
     * Helper method to find colliding with objects in the game
     * returns object like brick or paddle
     */
    private GObject getCollidingObject() {

        if ((getElementAt(ball.getX(), ball.getY())) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt((ball.getX() + BALL_DIAMETER * 2), ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_DIAMETER * 2, ball.getY());
        } else if (getElementAt(ball.getX(), (ball.getY() + BALL_DIAMETER * 2)) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER * 2);
        } else if (getElementAt((ball.getX() + BALL_DIAMETER * 2), (ball.getY() + BALL_DIAMETER * 2)) != null) {
            return getElementAt(ball.getX() + BALL_DIAMETER * 2, ball.getY() + BALL_DIAMETER * 2);
        }

        // returns null if there are no objects present
        else {
            return null;
        }
    }

    /*
     * Helper method that used to build several rows
     * of the bricks
     */
    private Color defineColor(int rowNumber) {
        Color outputColor;
        switch (rowNumber) {
            case (0):
            case (1):
                outputColor = Color.RED;
                break;
            case (2):
            case (3):
                outputColor = Color.ORANGE;
                break;
            case (4):
            case (5):
                outputColor = Color.YELLOW;
                break;
            case (6):
            case (7):
                outputColor = Color.GREEN;
                break;
            case (8):
            case (9):
                outputColor = Color.CYAN;
                break;
            default:
                outputColor = Color.WHITE;
        }
        return outputColor;
    }

    /*
     * Make mini animation at the end of the game
     * takes integer as a parameter which could be 0 or 1
     * 0 - the method will print "GAME OVER"
     * 1 - the method will print "VICTORY"
     */
    private void endGame(int winLose) {
        int fontSize = 60;
        int animationFrame = 20;
        GLabel resultLabel;
        removeAll(); // removes all unnecessary objects like paddle in the end of the game

        if (winLose == 1)
            resultLabel = new GLabel("VICTORY!!!");
        else
            resultLabel = new GLabel("GAME OVER!!!");

        resultLabel.setVisible(true);
        resultLabel.setColor(Color.RED);

        for (int i = 0; i < fontSize; i += 3) {
            double labelX = getWidth() / 2.0 - resultLabel.getWidth() / 2.0;
            double labelY = getHeight() / 2.0 - resultLabel.getHeight() / 2.0;
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, i);
            resultLabel.setFont(font);
            resultLabel.setLocation(labelX, labelY);
            add(resultLabel);
            pause(animationFrame);
        }
    }

    /*
     * Generates velocity of the ball
     * velocityX and -velocityX regulates which side the ball will move next
     * have equal chances to appear at the beginning of the game
     */
    private void velocityGenerator() {
        RandomGenerator rgen = RandomGenerator.getInstance();

        velocityY = GAME_PACE;
        velocityX = GAME_PACE;
        if (rgen.nextBoolean(0.5))
            velocityX = -velocityX;

    }

    /*
     * prints starting instruction to start the game, while instruction is active
     * blocking start of the game
     */
    private void printClickToStart() {
        /*
         *  be careful to change this variables because it is blocking the game start
         *  while label is on the screen
         */
        GLabel clickToStart = new GLabel("CLICK ON WINDOW TO START THE GAME");
        double labelX = getWidth() / 2.0 - clickToStart.getWidth() / 2.0;
        double labelY = getHeight() / 2.0 + BALL_DIAMETER * 2;

        clickToStart.setColor(Color.BLACK);
        clickToStart.setFont("Times New Roman-12");
        add(clickToStart, labelX, labelY);

        waitForClick();  // waits until user click on the screen
        remove(clickToStart);  // removes label from the screen
    }
}
