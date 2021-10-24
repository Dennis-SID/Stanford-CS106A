package com.cscourse.week11.dsidelnik.assignment11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class Assignment11Part1 {

    public static Color[][] imageMatrix;

    public static void main(String [] args) {
        String filePath = "assets/test4.jpg";
        if (args.length > 0) filePath = args[0];

        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            ColorModel model = image.getColorModel();

            ImageMatrix imageMatrix = new ImageMatrix(image);
            Node[][] nodes = imageMatrix.getNodes();
            System.out.println("The nodes is : " + nodes);
            System.out.println("Nodes height:  " + nodes.length);
            System.out.println("Nodes width:  " + nodes[0].length);
            System.out.println("Nodes first element:  " + nodes[0][0]);
            System.out.println("Nodes first element red color:  " + nodes[0][0].getRedColor());
            System.out.println("Nodes first element is visited:  " + nodes[0][0].isVisited());
            nodes[0][0].setAsVisited();
            System.out.println("Nodes first element is visited: " + nodes[0][0].isVisited());

            if (model.hasAlpha()) {

            }

           /* imageMatrix = new Color[image.getHeight()][image.getWidth()];
            System.out.println(imageMatrix[0][0]);*/

            /*for (int height = 0; height < image.getHeight(); height++) {
                for (int width = 0; width < image.getWidth(); width++) {
                    imageMatrix[height][width] = new Color(image.getRGB(width, height));
                }
            }
*/


        } catch (IOException e) {
            System.out.println("Something went wrong on the reading stage");
            e.printStackTrace();
        }


    }
}
