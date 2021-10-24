package com.cscourse.week11.dsidelnik.assignment11;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageAnalyzer {

    private double silhouettesCounter;
    private Color backGroundColor;

    public void getBgColor(Node[][] imageMatrix) {
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

        for (Color color : colors.keySet()) {
            if (counter == 0) {
                bgColor = color;
                appearances = colors.get(color);
                continue;
            }
            Integer current = colors.get(color);
            if (appearances < current) {
                appearances = current;
                bgColor = color;
            }
            counter++;
        }
        backGroundColor = bgColor;
    }
}
