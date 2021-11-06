package com.cscourse.week12.dsidelnik.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


/**
 * Simple silhouettes counter
 * use breadth first search method without recursion to find silhouettes in a picture
 * supports black and white images only, transparent images are not supported either
 * to get the result use public method count which gets file name from the command line
 * or test.jpg as a default
 */
public class SilhouettesCounter {

    /**
     * Used in program to divide pixels with black or white colors
     */
    private final int COLOR_THRESHOLD = 130;

    /**
     * coefficient that defines size of the noise blurs in a picture
     * by default it's 10% of size of the biggest silhouette in a picture
     */
    private final double GARBAGE_COEFFICIENT = 0.1;

    /* used to store data about visited and unvisited pixel through analysis */
    private boolean[][] visited;

    /* used to store background color */
    private int bgColor;

    /**
     * Analyze image, turns image into two dimensional integer array where stores 1 or 0 depending on a
     * color in the each pixel, than via breadth first search algorithm defines unvisited pixel
     * and counts number of silhouettes in a picture
     *
     * @param fileName file path (received from the command line or test.jpg by the default)
     * @return number of silhouettes in a picture
     * @throws IOException if file not found or file path is incorrect
     */
    public int count(String fileName) throws IOException {
        BufferedImage image = getImage(fileName);
        int[][] digitalImage = getImageDigitalRepresentation(image);
        visited = new boolean[image.getHeight()][image.getWidth()];
        List<Integer> allSilhouettes = new ArrayList<>();
        bgColor = bgColorDefiner(digitalImage);

        for (int height = 0; height < digitalImage.length; height++) {
            for (int width = 0; width < digitalImage[0].length; width++) {
                if (!visited[height][width] && !isBgColor(digitalImage, width, height)) {
                    allSilhouettes.add(BFS(digitalImage, width, height));
                }
            }
        }
        return garbageRemover(allSilhouettes).size();
    }

    /* gets image from the file system, takes path to the file as a parameter */
    private BufferedImage getImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
    }

    /**
     * Gets buffered image, process it and returns integer two dimensional array
     * integers in the array represents colors (1 - white, 0 - black)
     * @param image buffered image
     * @return two dimensional array of integer values
     */
    private int[][] getImageDigitalRepresentation(BufferedImage image) {
        int[][] resultArray = new int[image.getHeight()][image.getWidth()];
        for (int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                resultArray[height][width] = brightnessDefiner(new Color(image.getRGB(width, height)));
            }
        }
        return resultArray;
    }

    /* used to make digital representation of image
    * output bright pixels as 1 and dark pixels as 0
    * threshold are established as a final variable
    */
    private int brightnessDefiner(Color color) {
        if (color.getBlue() > COLOR_THRESHOLD) return 1;
        else return 0;
    }

    /**
     * Breadth first search algorithm, implementation
     * @param digImg digital representation of the image
     * @param x point on a x coordinate axis
     * @param y point on a y coordinate axis
     * @return number of pixel in silhouette which was processed
     */
    private int BFS(int[][] digImg, int x, int y) {
        int pixelCounter = 0;
        Queue<Pixel> uninvestigatedNodes = new LinkedList<>();

        visited[y][x] = true;
        pixelCounter++;
        uninvestigatedNodes.add(new Pixel(x, y));
        while (!uninvestigatedNodes.isEmpty()) {
            Pixel currPixel = uninvestigatedNodes.poll();
            x = currPixel.getCoordX();
            y = currPixel.getCoordY();

            if (x - 1 > 0 && !visited[y][x - 1] && !isBgColor(digImg, x - 1, y)) {
                uninvestigatedNodes.add(new Pixel(x - 1, y));
                visited[y][x - 1] = true;
                pixelCounter++;
            }

            if (x + 1 < digImg[0].length - 1 && !visited[y][x + 1] && !isBgColor(digImg, x + 1, y)) {
                uninvestigatedNodes.add(new Pixel(x + 1, y));
                visited[y][x + 1] = true;
                pixelCounter++;
            }

            if (y - 1 > 0 && !visited[y - 1][x] && !isBgColor(digImg, x, y - 1)) {
                uninvestigatedNodes.add(new Pixel(x, y - 1));
                visited[y - 1][x] = true;
                pixelCounter++;
            }

            if (y + 1 < digImg.length - 1 && !visited[y + 1][x] && !isBgColor(digImg, x, y + 1)) {
                uninvestigatedNodes.add(new Pixel(x, y + 1));
                visited[y + 1][x] = true;
                pixelCounter++;
            }
        }
        return pixelCounter;
    }

    /* defines whether pixel represents background color */
    private boolean isBgColor(int[][] digImg, int x, int y) {
        return digImg[y][x] == bgColor;
    }

    /* go through borders of the image, defines which pixel are more (light or dark)
    * following this returns background color
    */
    private int bgColorDefiner(int[][] digitalImage) {
        int whiteCounter = 0;
        int blackCounter = 0;

        // trace upper border
        for (int i = 0; i < digitalImage[0].length; i++) {
            if (digitalImage[0][i] == 1) whiteCounter++;
            else blackCounter++;
        }

        // trace bottom border
        for (int i = 0; i < digitalImage[0].length; i++) {
            if (digitalImage[digitalImage.length - 1][i] == 1) whiteCounter++;
            else blackCounter++;
        }

        // trace leftmost border
        for (int i = 0; i < digitalImage.length; i++) {
            if (digitalImage[i][0] == 1) whiteCounter++;
            else blackCounter++;
        }

        // trace rightmost border
        for (int i = 0; i < digitalImage.length; i++) {
            if (digitalImage[i][digitalImage[0].length - 1] == 1) whiteCounter++;
            else blackCounter++;
        }

        // defines which color occurs more often in image
        if (whiteCounter >= blackCounter) return 1;
        else return 0;
    }

    /* takes list with all numbers of pixel which was occured in image
    * and returns list without garbage blurs, defines size of blur (garbage)
    * using GARBAGE_COEFFICIENT final variable
    */
    private List<Integer> garbageRemover(List<Integer> list) {
        List<Integer> resultList = new ArrayList<>();
        if (list == null || list.size() < 1) return resultList;

        int largerSilhouette = list.get(0);
        int garbageSize = 0;
        for (int currSil : list) {
            if (largerSilhouette < currSil) {
                largerSilhouette = currSil;
            }
        }
        garbageSize = (int) (GARBAGE_COEFFICIENT * largerSilhouette);
        for (int currSil : list) {
            if (currSil > garbageSize) {
                resultList.add(currSil);
            }
        }
        return resultList;
    }
}
