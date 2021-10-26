package com.cscourse.week11.dsidelnik.assignment11;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that gets image and turns it into matrix
 * prepares image to processing
 */
public class ImageMatrix {
    private Node[][] imageMatrix;

    public ImageMatrix() {
    }

    public ImageMatrix(BufferedImage image) {
        this();
        Color[][] colorMatrix = createColorMatrix(image);
        pixelConnectionMaker(colorMatrix);
        imageMatrix = blackAndWhiteMaker(imageMatrix);
    }

    /**
     * Gets image and returns two dimensional array (matrix) of image
     *
     * @param image input image
     * @return image as pixel matrix
     */
    private Color[][] createColorMatrix(BufferedImage image) {
        Color[][] imageColorMatrix = new Color[image.getHeight()][image.getWidth()];

        for (int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                imageColorMatrix[height][width] = new Color(image.getRGB(width, height));
            }
        }
        return imageColorMatrix;
    }

    /**
     * Make traversion through image matrix and make connections
     * between neighbour pixels.
     * creates graph of pixels
     *
     * @param colorMatrix two dimensional array
     */
    private void pixelConnectionMaker(Color[][] colorMatrix) {
        imageMatrix = new Node[colorMatrix.length][colorMatrix[0].length];
        for (int height = 0; height < colorMatrix.length; height++) {
            for (int width = 0; width < colorMatrix[0].length; width++) {
                imageMatrix[height][width] = new Node(width, height, colorMatrix[height][width]);
            }
        }

        for (int height = 0; height < colorMatrix.length; height++) {
            for (int width = 0; width < colorMatrix[0].length; width++) {
                Node node = imageMatrix[height][width];

                if (width == 0 && height == 0) {
                    node.addNode(imageMatrix[height][width + 1]);
                    node.addNode(imageMatrix[height + 1][width]);
                } else if (width != 0 && width < imageMatrix[0].length - 1 && height == 0) {
                    node.addNode(imageMatrix[height][width - 1]);
                    node.addNode(imageMatrix[height][width + 1]);
                    node.addNode(imageMatrix[height + 1][width]);
                } else if (height == 0 && width == imageMatrix[0].length - 1) {
                    node.addNode(imageMatrix[height][width - 1]);
                    node.addNode(imageMatrix[height + 1][width]);
                } else if (height > 0 && height < imageMatrix.length - 1 && width == 0) {
                    node.addNode(imageMatrix[height - 1][width]);
                    node.addNode(imageMatrix[height + 1][width]);
                    node.addNode(imageMatrix[height][width + 1]);
                } else if (height > 0 && height < imageMatrix.length - 1 && width > 0 && width < imageMatrix[0].length - 1) {
                    node.addNode(imageMatrix[height][width - 1]);
                    node.addNode(imageMatrix[height][width + 1]);
                    node.addNode(imageMatrix[height - 1][width]);
                    node.addNode(imageMatrix[height + 1][width]);
                } else if (height > 0 && height < imageMatrix.length - 1 && width == imageMatrix[0].length - 1) {
                    node.addNode(imageMatrix[height - 1][width]);
                    node.addNode(imageMatrix[height][width - 1]);
                    node.addNode(imageMatrix[height + 1][width]);
                } else if (height == imageMatrix.length - 1 && width == 0) {
                    node.addNode(imageMatrix[height - 1][width]);
                    node.addNode(imageMatrix[height][width + 1]);
                } else if (height == imageMatrix.length - 1 && width > 0 && width < imageMatrix[0].length - 1) {
                    node.addNode(imageMatrix[height - 1][width]);
                    node.addNode(imageMatrix[height][width - 1]);
                    node.addNode(imageMatrix[height][width + 1]);
                } else if (height == imageMatrix.length - 1 && width == imageMatrix[0].length - 1) {
                    node.addNode(imageMatrix[height][width - 1]);
                    node.addNode(imageMatrix[height - 1][width]);
                }
            }
        }
    }

    /**
     * Returns node list that represents all neighbour nodes
     *
     * @return node graph represented as tow dimensional node matrix
     */
    public Node[][] getNodes() {
        return imageMatrix;
    }

    /**
     * Traverse all pixels check color and maximize or minimize it (turns all colors into black or white)
     * prepares iamge for silhouettes analyzing
     *
     * @param matrix image represented as pixel graph
     * @return matrix with black and white pixels
     */
    public Node[][] blackAndWhiteMaker(Node[][] matrix) {
        for (int height = 0; height < matrix.length; height++) {
            for (int width = 0; width < matrix[0].length; width++) {
                int red = matrix[height][width].getRedColor();
                int green = matrix[height][width].getGreenColor();
                int blue = matrix[height][width].getBlueColor();
                Color color = null;

                if (red > 130 && green > 130 && blue > 130) {
                    red = 255;
                    green = 255;
                    blue = 255;
                    color = Color.WHITE;
                } else {
                    red = 0;
                    green = 0;
                    blue = 0;
                    color = Color.BLACK;
                }
                matrix[height][width].setRedColor(red);
                matrix[height][width].setGreenColor(green);
                matrix[height][width].setBlueColor(blue);
                matrix[height][width].setColor(color);
            }
        }
        return matrix;
    }

    /**
     * Makes grayscale image
     *
     * @param matrix pixel graph
     * @return grayscale image matrix
     */
    public Node[][] makeGreyScale(Node[][] matrix) {

        for (int height = 0; height < matrix.length; height++) {
            for (int width = 0; width < matrix[0].length; width++) {
                int red = matrix[height][width].getRedColor();
                int green = matrix[height][width].getGreenColor();
                int blue = matrix[height][width].getBlueColor();

                red *= 0.299;
                green *= 0.587;
                blue *= 0.114;

                matrix[height][width].setRedColor(red);
                matrix[height][width].setGreenColor(green);
                matrix[height][width].setBlueColor(blue);
            }
        }
        return matrix;
    }

}
