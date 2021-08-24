package com.cscourse.week1.dsidelnik.assignment1;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Section1 Task1
 * Class draws a face of the robot
 * picture centred on the screen
 */
public class Section1Task1 extends WindowProgram {

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    private static final int HEAD_WIDTH = 100;

    private static final int HEAD_HEIGHT = 100;

    private static final int MOUTH_WIDTH = 50;

    private static final int MOUTH_HEIGHT = 10;

    /*
     * Instance variables for two rectangles
     * one, bigger, black and white smaller which makes a face
     * other objects (eyes, mouth) are depends on face and face2 size
     * so other objects uses face and face2 methods (getWidth(), getHeight())
     */
    private GRect face;
    private GRect face2;

    /**
     * Main action method that starts a program
     */
    public void run() {
        drawFace();
        drawEyes();
        drawMouth();
    }

    /*
     * Helper method that draws main application (face)
     */
    private void drawFace() {
        face = new GRect(HEAD_WIDTH, HEAD_HEIGHT);
        face2 = new GRect(HEAD_WIDTH - HEAD_WIDTH / 10.0, HEAD_HEIGHT - HEAD_HEIGHT / 10.0);
        face.setFilled(true);
        face2.setFilled(true);
        face.setColor(Color.BLACK);
        face2.setColor(Color.WHITE);
        face.setVisible(true);
        face2.setVisible(true);
        add(face, getWidth() / 2.0 - face.getWidth() / 2.0, getHeight() / 2.0 - face.getHeight() / 2.0);
        add(face2, getWidth() / 2.0 - face2.getWidth() / 2.0, getHeight() / 2.0 - face2.getHeight() / 2.0);
    }

    /*
     * Draws left and right eyes on tha face
     * takes screen and face2 sizes to draw and center objects
     */
    private void drawEyes() {
        double eyeRadius = face2.getWidth() / 10;
        double leftEyeX = getWidth() / 2.0 - face2.getWidth() / 4.0;
        double rightEyeX = getWidth() / 2.0 + face2.getWidth() / 4.0 - eyeRadius;
        double eyesY = getHeight() / 2.0 - face2.getHeight() / 4.0;

        GOval leftEye = new GOval(face2.getWidth() / 10.0, face2.getHeight() / 10.0);
        GOval rightEye = new GOval(face2.getWidth() / 10.0, face2.getHeight() / 10.0);
        leftEye.setFilled(true);
        rightEye.setFilled(true);
        leftEye.setFillColor(Color.BLACK);
        rightEye.setFillColor(Color.BLACK);
        leftEye.setVisible(true);
        rightEye.setVisible(true);
        add(leftEye, leftEyeX, eyesY );
        add(rightEye, rightEyeX, eyesY );
    }

    /*
     * Draws mouth, size of the mouth regulates by the final variables
     */
    private void drawMouth() {
        double mouthX = getWidth() / 2.0 - MOUTH_WIDTH / 2.0;
        double mouthY = getHeight() / 2.0 + face2.getHeight() / 4.0;

        GRect mouth = new GRect(MOUTH_WIDTH, MOUTH_HEIGHT);
        mouth.setFilled(true);
        mouth.setFillColor(Color.BLACK);
        mouth.setVisible(true);
        add(mouth, mouthX, mouthY);
    }
}
