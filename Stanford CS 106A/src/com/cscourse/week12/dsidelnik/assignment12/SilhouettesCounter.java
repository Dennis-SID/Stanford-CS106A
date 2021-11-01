package com.cscourse.week12.dsidelnik.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class SilhouettesCounter {

    private final int COLOR_THRESHOLD = 130;
    private final double GARBAGE_COEFFICIENT = 0.1;
    private boolean[][] visited;
    private int bgColor;

    public int count(String fileName) throws IOException {
        BufferedImage image = getImage(fileName);
        int [][] digitalImage = getImageDigitalRepresentation(image);
        visited = new boolean[image.getHeight()][image.getWidth()];
        List<Integer> allSilhouettes = new ArrayList<>();
        bgColor = bgColorDefiner(digitalImage);

        return 0;
    }

    private BufferedImage getImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
    }

    private int[][] getImageDigitalRepresentation(BufferedImage image) {
        int[][] resultArray = new int [image.getHeight()][image.getWidth()];
        for (int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                resultArray[height][width] = brightnessDefiner(new Color(image.getRGB(width, height)));
            }
        }
        return resultArray;
    }

    private int brightnessDefiner(Color color) {
        if (color.getBlue() > COLOR_THRESHOLD) return 1;
        else return 0;
    }

    private int BFS(int[][] digitalImage, int x, int y) {
        int pixelCounter = 0;
        Queue

    }

    private int bgColorDefiner(int [][] digitalImage) {
        int whiteCounter = 0;
        int blackCounter = 0;
        for (int i = 0; i < digitalImage[0].length; i++) {
            if (digitalImage[0][i] == 1) whiteCounter++;
            else blackCounter++;
        }

        for (int i = 0; i < digitalImage[0].length; i++) {
            if (digitalImage[digitalImage.length - 1][i] == 1) whiteCounter++;
            else blackCounter++;
        }

        for (int i = 0; i < digitalImage[0].length; i++) {
            if (digitalImage[i][0] == 1) whiteCounter++;
            else blackCounter++;
        }

        for (int i = 0; i < digitalImage[0].length; i++) {
            if (digitalImage[i][digitalImage[0].length - 1] == 1) whiteCounter++;
            else blackCounter++;
        }
        return Math.max(whiteCounter, blackCounter);
    }




}
