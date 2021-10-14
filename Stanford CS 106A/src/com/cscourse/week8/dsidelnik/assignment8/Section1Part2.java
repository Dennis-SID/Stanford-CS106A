package com.cscourse.week8.dsidelnik.assignment8;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Section1Part2 {

    volatile static boolean isFrameReadyToDraw = true;

    public static void main (String [] args) {

        final int screenWidth = 1200;
        final int screenHeight = 800;

        JFrame frame = new JFrame();
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);

        ImageIcon  imageIcon = new ImageIcon(image);
        JLabel picLabel = new JLabel(imageIcon);

        BorderLayout borderLayout = new BorderLayout();
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);


        frame.setVisible(true);

        Render render = new Render();
        Model model = new Model();

        long lastTime = System.currentTimeMillis();

        while (frame.isVisible()) {
            long time = System.currentTimeMillis();
            model.update (time - lastTime);
            lastTime = time;

            /*if (isFrameReadyToDraw) {
                isFrameReadyToDraw = false;*/
                render.draw(image, model);
                SwingUtilities.invokeLater(() -> {
                    frame.repaint();
                    /*isFrameReadyToDraw = true;*/
                });
            /*}*/
        }
    }
}

class Point {
    float x;
    float y;
    float z;

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}

class Model {
    public static final float INITIAL_Z_COORD = -3;

    public static final float MOTION_SPEED = 0.001f;


    List<Point> points = new ArrayList<>();

    public void update (long elapsedTime) {
        float birthChance = 0.0f;

        if (random(0, 1) < birthChance) {
            points.add(new Point(random(-1, 1), random(-1, 1), INITIAL_Z_COORD));
        }

        for (Point point : points) {
            point.z += elapsedTime * MOTION_SPEED;
        }

        points.removeIf(point -> point.z >= 0);
    }

    private float random(int lowerBorder, int highBorder) {
        return (float) (lowerBorder + (highBorder - lowerBorder) * Math.random());
    }

    public List<Point> getPoints() {
        return points;
    }
}

class Render {
    public void draw(BufferedImage image, Model model) {
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(new Color(0, 0, 0));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (Point point : model.getPoints()) {
            int sx = image.getWidth() / 2 + (int) (image.getWidth() / 2 * point.x / point.z);
            int sy = image.getHeight() / 2 + (int) (image.getHeight() / 2 * point.x / point.z);
            if (sx > image.getWidth() && sx >= 0 && sy < image.getHeight() && sy >= 0) {
                int color = 255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD)));
                image.setRGB(sx, sy, 0xff000000 | color << 16 | color << 8 | color);
            }
        }
    }
}