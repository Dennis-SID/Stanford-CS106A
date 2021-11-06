package com.cscourse.week11.dsidelnik.assignment11.memoryoptimized;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Simple silhouettes counter
 * use deep first search method based on recursion to find silhouettes in a picture
 * supports black and white images only, transparent images are not supported either
 * to get the result use public method count which gets file name from the command line
 * or test.jpg as a default
 */
public class SilhouettesCounter {

    /**
     * coefficient that defines size of the noise blurs in a picture
     * by default it's 10% of size of the biggest silhouette in a picture
     */
    private static final double TRASH_COEFFICIENT = 0.1;

    /**
     * Used in program to divide pixels with black or white colors
     */
    private static final int COLOR_THRESHOLD = 130;

    /* used to store background color */
    private static int bgColor;
    /* used to store data about visited and unvisited pixel through analysis */
    boolean[][] visited;
    /* used to count number of pixel at each silhouette for further analysis */
    private static int silhouettePixels = 0;

    /**
     * Analyze image, turns image into two dimensional integer array where stores 1 or 0 depending on a
     * color in the each pixel, than via deep first search algorithm defines unvisited pixel
     * and counts number of silhouettes in a picture
     *
     * @param args file path (received from the command line or test.jpg by the default)
     * @return number of silhouettes in a picture
     * @throws IOException if sile not found or file path is incorrect
     */
    public int count(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File(getPath(args)));
        int[][] imageMatrix = imageColorAnalyzer(image);
        bgColor = getBackgroundColor(imageMatrix);
        visited = new boolean[image.getHeight()][image.getWidth()];
        ArrayList<Integer> silhouettesSizes = new ArrayList<>();

        for (int height = 0; height < imageMatrix.length; height++) {
            for (int width = 0; width < imageMatrix[0].length; width++) {
                if (!visited[height][width] && notBackground(imageMatrix, width, height)) {
                    deepFirstSearch(width, height, imageMatrix);
                    silhouettesSizes.add(silhouettePixels);
                    silhouettePixels = 0;
                }
            }
        }
        return silhouettesFilter(silhouettesSizes, garbageSizeDefiner(silhouettesSizes));
    }

    /* returns file path from the command line or test.jpg by the default */
    private String getPath(String[] args) {

        if (args.length > 0) {
            return args[0];
        } else {
            return "test.jpg";
        }
    }

    /* turns buffered color image into integer array, numbers in array represents colors black or white */
    private int[][] imageColorAnalyzer(BufferedImage image) {
        int[][] resultMatrix = new int[image.getHeight()][image.getWidth()];

        for (int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currColor = new Color(image.getRGB(width, height));
                resultMatrix[height][width] = blackOrWhiteColorDefiner(currColor);
            }
        }
        return resultMatrix;
    }

    /* depending on threshold define whether color is black or white */
    private int blackOrWhiteColorDefiner(Color color) {
        if (color.getBlue() > COLOR_THRESHOLD) return 1;
        else return 0;
    }

    /* go through borders of the image to define background color */
    private int getBackgroundColor(int[][] imageMatrix) {
        int counterWhite = 0;
        int counterBlack = 0;

        //check upper border
        for (int i = 0; i < imageMatrix[0].length; i++) {
            if ((imageMatrix[0][i] == 0)) counterBlack++;
            else counterWhite++;
        }

        // check bottom border
        for (int i = 0; i < imageMatrix[0].length; i++) {
            if (imageMatrix[imageMatrix.length - 1][i] == 0) counterBlack++;
            else counterWhite++;
        }

        //check leftmost border
        for (int i = 0; i < imageMatrix.length; i++) {
            if (imageMatrix[i][0] == 0) counterBlack++;
            else counterWhite++;
        }

        //check rightmost border
        for (int i = 0; i < imageMatrix.length; i++) {
            if (imageMatrix[i][imageMatrix[0].length - 1] == 0) counterBlack++;
            else counterWhite++;
        }

        // what color was encountered more often in the image will be counted as background color
        if (!(counterBlack <= counterWhite)) return 0;
        else return 1;
    }

    /* deep first search algorithm recursive implemented */
    private void deepFirstSearch(int x, int y, int[][] img) {
        silhouettePixels++;
        visited[y][x] = true;

        /* check of left pixel  */
        if (x - 1 > 0 && notBackground(img, x - 1, y) && !visited[y][x - 1]) {
            deepFirstSearch(x - 1, y, img);
        }
        /* check of right pixel  */
        if (x + 1 < img[0].length && notBackground(img, x + 1, y) && !visited[y][x + 1]) {
            deepFirstSearch(x + 1, y, img);
        }
        /* check of upper pixel */
        if (y - 1 > 0 && notBackground(img, x, y - 1) && !visited[y - 1][x]) {
            deepFirstSearch(x, y - 1, img);
        }
        /* check of bottom pixel */
        if (y + 1 < img.length && notBackground(img, x, y + 1) && !visited[y + 1][x]) {
            deepFirstSearch(x, y + 1, img);
        }
    }

    private boolean notBackground(int[][] imageMatrix, int x, int y) {
        return imageMatrix[y][x] != bgColor;
    }

    /* takes the biggest silhouette on an image and finds noise (garbage) size using
     * coefficient (10% by default)
     */
    private int garbageSizeDefiner(ArrayList<Integer> silhouettesSizes) {
        int biggestSilhouette = 0;

        for (int curr : silhouettesSizes) {
            if (curr > biggestSilhouette) biggestSilhouette = curr;
        }

        return (int) Math.round(biggestSilhouette * TRASH_COEFFICIENT);
    }

    /* depending on the minimal silhouettes size founded earlier counts number of silhouettes
     * without noise blurs (garbage)
     */
    private int silhouettesFilter(ArrayList<Integer> allSilhouettes, int silhouetteMinSize) {
        int silhouettesWithoutGarbage = 0;
        for (int currSilhouette : allSilhouettes) {
            if (currSilhouette > silhouetteMinSize) silhouettesWithoutGarbage++;
        }
        return silhouettesWithoutGarbage;
    }
}
