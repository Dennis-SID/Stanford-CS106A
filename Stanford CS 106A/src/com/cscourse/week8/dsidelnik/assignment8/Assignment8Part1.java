package com.cscourse.week8.dsidelnik.assignment8;

import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Assignment8Part1 extends WindowProgram {

    /**
     * Width and height of application window in pixels
     */
    private static final int MENU_BAR_HEIGHT = 23;
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500 + MENU_BAR_HEIGHT;

    /**
     * List that stores all GRect objects and allows to manipulate them
     */
    private ArrayList<GRect> squareList = new ArrayList<>();
    private ArrayList<Boolean> indicatorList;

    /**
     * Set number of squares on the screen
     */
    public static final double SQUARE_NUMBER = 10;

    /**
     * Set the default color of the squares
     */
    public static final Color SQUARE_COLOR = Color.GREEN;

    /**
     * Sets pause time between square movements (regualtes speed of animation)
     */
    private static final int PAUSE_TIME = 100;

    /**
     * gets square GRect object mouse pointer hover on
     */
    private static GObject mouseHoverSquare;

    /**
     * Main action method that starts the program
     */
    public void run() {
        addMouseListeners();
        drawRectangles(); // draws green squares through window border
        setBoolList(); // sets variables in indicatorList to false by default

        // creation of two threads for animation of red and blue squares
        Thread blueSquare = new Thread(() -> animation(Color.BLUE));
        Thread redSquare = new Thread(() -> animation(Color.RED));

        // starts two threads
        blueSquare.start();
        redSquare.start();
    }

    /**
     * Draws upper, bottom, left and right sequence of squares
     * squares are drawn on the screen and put into list in a sequential order to
     * to sort it and manipulate one after another
     */
    private void drawRectangles() {
        double squareSize = getWidth() / SQUARE_NUMBER;
        double squareX = 0.0;
        double squareY = 0.0;

        for (int i = 0; i < SQUARE_NUMBER; i++) {
            drawSingleSquare(squareSize, squareSize, squareX, squareY);
            squareX += squareSize;
        }
        squareX -= squareSize;
        squareY = squareSize;

        for (int i = 0; i < SQUARE_NUMBER - 1; i++) {
            drawSingleSquare(squareSize, squareSize, squareX, squareY);
            squareY += squareSize;
        }
        squareY -= squareSize;
        squareX = getWidth() - squareSize * 2;

        for (int i = 0; i < SQUARE_NUMBER - 1; i++) {
            drawSingleSquare(squareSize, squareSize, squareX, squareY);
            squareX -= squareSize;
        }
        squareY = getHeight() - squareSize * 2;
        squareX = 0.0;

        for (int i = 0; i < SQUARE_NUMBER - 2; i++) {
            drawSingleSquare(squareSize, squareSize, squareX, squareY);
            squareY -= squareSize;
        }
    }

    /**
     * Creates a single GRect element and adds it to the squareList list
     *
     * @param width  width of the rectangle
     * @param height height of the rectangle should be the same as width to get square
     * @param x      drawing starting point on X coordinate axis
     * @param y      drawing starting point on Y coordinate axis
     */
    private void drawSingleSquare(double width, double height, double x, double y) {
        GRect square = new GRect(width, height);
        square.setFilled(true);
        square.setColor(Color.BLACK);
        square.setFillColor(SQUARE_COLOR);
        square.setLocation(x, y);
        squareList.add(square);
    }

    /**
     * After changes to GRect objects which squareList store
     * this method put changed squares (GRect objects) on a canvas
     * another words redraw squares on a canvas
     * used by animation method
     */
    private void updateRectangles() {
        for (GRect square : squareList) {
            add(square);
        }
    }

    /**
     * Animation logic realized in this method
     * in first part makes initial settings and then go through
     * list of GObject objects and change their colors according
     * to animation rules
     *
     * @param color changes a color of square from default green color to
     *              method parameter color
     */
    private void animation(Color color) {
        // uses random numbers to randomly choose starting position of the square
        // and uses random boolean generator to randomly choose direction of square animation
        Random randomNumber = new Random();
        int idxSquare = randomNumber.nextInt(squareList.size() - 1); // random starter position
        int step = randomNumber.nextBoolean() ? 1 : -1; // random direction movement of animation

        // searching for square that has not been colored already by another thread or method
        while (indicatorList.get(idxSquare)) {
            idxSquare = indexChanger(idxSquare, step);
        }
        // sets starting position of animation
        indicatorList.set(idxSquare, true);
        squareList.get(idxSquare).setFillColor(color);

        // main animation logic is in this loop
        while (true) {
            updateRectangles(); // draws squares on a screen with changed colors

            //check whether the next GObject is has default green color or not
            if (!checkForBlock(idxSquare, step)) {
                squareList.get(idxSquare).setFillColor(SQUARE_COLOR); // sets current square to a default color
                indicatorList.set(idxSquare, false); // indicates that square has default green color

                idxSquare = indexChanger(idxSquare, step); // changes index to get access to the next square in the list

                squareList.get(idxSquare).setFillColor(color); // sets color of the next square to a parameter color
                indicatorList.set(idxSquare, true); // indicates that square has been colored
            } else {
                step = directionChanger(step); // changes positive ste variable to negative and vice versa
            }

            pause(PAUSE_TIME); // regulates animation speed
        }
    }

    /**
     * Helper method used by animation method
     * used to change square animation direction (step variable from positive to negative
     * and vise versa)
     */
    private int directionChanger(int index) {
        return (index > 0) ? -index : Math.abs(index);
    }

    /**
     * Helper method
     * Used at the beginning of the program implementation
     * takes instance list boolean variable invokes iterator
     * and assigns false to each list value
     */
    private void setBoolList() {
        indicatorList = new ArrayList<>(squareList.size());
        for (int i = 0; i < squareList.size(); i++) {
            indicatorList.add(false);
        }
    }

    /**
     * Helper method
     * used by animation method in the eternal loop to check
     * whether the next square is colored or not
     * also has checking for mouse pointer hover
     * (if mouse pointer on a square at that time the method will return true)
     */
    private boolean checkForBlock(int index, int step) {
        if (index == squareList.size() - 1 && step > 0) index = 0;
        else if (index == 0 && step < 0) index = squareList.size() - 1;
        else index += step;
        if (squareList.get(index) == mouseHoverSquare) return true;
        return indicatorList.get(index);
    }

    /**
     * Helper method
     * used by animation method check whether index is not out of bounds (squareList size)
     * and increase it or decrease it by value of step variable (1 or -1 by default)
     * 1 or -1 one of two options decided randomly by Random class variable
     */
    private int indexChanger(int index, int step) {
        if (index == squareList.size() - 1 && step > 0) index = 0;
        else if (index == 0 && step < 0) index = squareList.size() - 1;
        else index += step;
        return index;
    }

    /**
     * Action listener that takes GObject mouse pointer moving on at that particular
     * time and put it to instance mouseHoverSquare variable
     *
     * @param e MouseEvent class variable
     */
    public void mouseMoved(MouseEvent e) {
        mouseHoverSquare = getElementAt(e.getX(), e.getY());
    }

}
