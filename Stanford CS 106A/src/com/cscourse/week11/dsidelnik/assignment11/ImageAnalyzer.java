package com.cscourse.week11.dsidelnik.assignment11;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Have method for analyzing background color of the image and
 * counter of silhouettes on a picture
 * uses deep first search algorithm to find silhouettes on a picture
 */
public class ImageAnalyzer {

    /**
     * traverse image and define most probable background color
     *
     * @param imageMatrix image turned to pixel graph (matrix)
     * @return background color
     */
    public Color getBgColor(Node[][] imageMatrix) {
        Map<Color, Integer> colors = new HashMap<>();
        Color bgColor = new Color(0, 0, 0);
        int counter = 0;
        int appearances = 0;
        for (int height = 0; height < imageMatrix.length; height++) {
            for (int width = 0; width < imageMatrix[0].length; width++) {
                if (colors.containsKey(imageMatrix[height][width].getColor())) {
                    Integer tmp = colors.get(imageMatrix[height][width].getColor());
                    colors.put(imageMatrix[height][width].getColor(), tmp + 1);
                } else {
                    colors.put(imageMatrix[height][width].getColor(), 1);
                }
            }
        }
        // check HashMap for the biggest number of appearances of the color
        for (Color color : colors.keySet()) {
            if (counter == 0) {
                bgColor = color;
                appearances = colors.get(color);
                counter++;
                continue;
            }
            Integer current = colors.get(color);
            if (appearances < current) {
                appearances = current;
                bgColor = color;
            }
            counter++;
        }
        return bgColor;
    }

    /**
     * Traverse all connected nodes with the same color, which wasn't visited yet
     * uses recursion
     *
     * @param node    yet unvisited node with the same color
     * @param bgColor background color
     */
    public void DFS(Node node, Color bgColor) {
        node.setAsVisited();
        List<Node> neighbours = node.getConnectedNodes();

        for (Node neighbourNode : neighbours) {
            if (!neighbourNode.isVisited() && !neighbourNode.getColor().equals(bgColor)) {
                DFS(neighbourNode, bgColor);
            }
        }
    }

    /**
     * Counts number of silhouettes in a picture
     *
     * @param imageMatrix black and white image
     * @param bgColor     background color found earlier
     * @return number of silhouettes on a picture
     */
    public int silhouettesCounter(Node[][] imageMatrix, Color bgColor) {
        int silCounter = 0;
        for (int height = 0; height < imageMatrix.length; height++) {
            for (int width = 0; width < imageMatrix[0].length; width++) {
                if (imageMatrix[height][width].getColor().equals(bgColor) &&
                        !imageMatrix[height][width].isVisited()) {
                    imageMatrix[height][width].setAsVisited();
                } else if (!imageMatrix[height][width].getColor().equals(bgColor) &&
                        !imageMatrix[height][width].isVisited()) {
                    DFS(imageMatrix[height][width], bgColor);
                    silCounter++;
                }
            }
        }
        return silCounter;
    }
}
