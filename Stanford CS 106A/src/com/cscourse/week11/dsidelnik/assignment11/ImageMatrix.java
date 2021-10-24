package com.cscourse.week11.dsidelnik.assignment11;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageMatrix {
    Node[][] imageMatrix;

    public ImageMatrix() {
    }

    public ImageMatrix(BufferedImage image) {
        this();
        Color[][] colorMatrix = createColorMatrix(image);
        createImageMatrix(colorMatrix);
    }

    private Color[][] createColorMatrix(BufferedImage image) {
        Color [][] imageColorMatrix = new Color[image.getHeight()][image.getWidth()];

        for (int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                imageColorMatrix[height][width] = new Color(image.getRGB(width, height));
            }
        }
        return imageColorMatrix;
    }

    private void createImageMatrix(Color[][] colorMatrix) {
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

    public Node[][] getNodes() {
        return imageMatrix;
    }
}
