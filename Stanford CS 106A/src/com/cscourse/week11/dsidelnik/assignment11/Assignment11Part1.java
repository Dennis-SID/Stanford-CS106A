package com.cscourse.week11.dsidelnik.assignment11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assignment11Part1 {

    public static void main(String[] args) {
        String filePath = "assets/test1.jpg";
        if (args.length > 0) filePath = "assets/" + args[0];

        try {
            // reads image
            BufferedImage image = ImageIO.read(new File(filePath));
            ImageAnalyzer imageAnalyzer = new ImageAnalyzer();

            //makes pixel graph
            ImageMatrix imageMatrix = new ImageMatrix(image);
            Node[][] nodes = imageMatrix.getNodes();
            Node[][] blackWhiteNodes = imageMatrix.blackAndWhiteMaker(nodes);

            // output size of image to user
            // defines background color of the image
            System.out.println("Image height: " + blackWhiteNodes.length + " Image width: " + blackWhiteNodes[0].length);
            Color bgColor = imageAnalyzer.getBgColor(blackWhiteNodes);

            // defines number of silhouettes on a picture and make output for user
            int numberOfSils = imageAnalyzer.silhouettesCounter(blackWhiteNodes, bgColor);
            System.out.println("THE NUMBER OF SILHOUETTES WERE FOUND ON A PICTURE IS(ARE) ===== " + numberOfSils + " =====");

        } catch (IOException e) {

            System.out.println("Something went wrong on the reading stage");
            e.printStackTrace();
        }
    }
}
