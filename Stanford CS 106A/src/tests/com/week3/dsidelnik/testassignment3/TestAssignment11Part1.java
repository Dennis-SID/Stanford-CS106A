package tests.com.week3.dsidelnik.testassignment3;

import com.cscourse.week11.dsidelnik.assignment11.ImageAnalyzer;
import com.cscourse.week11.dsidelnik.assignment11.ImageMatrix;
import com.cscourse.week11.dsidelnik.assignment11.Node;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestAssignment11Part1 {

    public static void main (String [] args) {
        String filePath = "assets/imgs/";
        try {
            assertEquals(1, filePath + "tst_01.jpg", "1");
            assertEquals(4, filePath + "tst_02.png", "2");
            assertEquals(2, filePath + "tst_03.jpg", "3");
            assertEquals(4, filePath + "tst_04.png", "4");
            assertEquals(3, filePath + "tst_05.jpg", "5");
            assertEquals(1, filePath + "tst_06.jpg", "6");
            assertEquals(13, filePath + "tst_07.jpg", "7");
            assertEquals(1, filePath + "tst_08.jpg", "8");
            assertEquals(35, filePath + "tst_10.jpg", "10");
            assertEquals(7, filePath + "tst_12.jpg", "12");
            assertEquals(13, filePath + "tst_16.bmp", "16");
            assertEquals(1, filePath + "tst_18.jpg", "18");
            assertEquals(3, filePath + "tst_20.jpg", "20");
            assertEquals(6, filePath + "tst_22.jpg", "22    ");
            assertEquals(20, filePath + "tst_23.jpg", "23    ");
            assertEquals(1, filePath + "tst_25.jpg", "25");
            assertEquals(13, filePath + "tst_29.jpg", "29");
            assertEquals(6, filePath + "tst_42.png", "42");
            assertEquals(3, filePath + "tst_46.jpeg", "46");
            assertEquals(1, filePath + "tst_47.png", "47");
            assertEquals(1, filePath + "tst_48.png", "48");
            assertEquals(4, filePath + "tst_49.png", "49");
            assertEquals(5, filePath + "tst_56.png", "56");
            assertEquals(10, filePath + "tst_57.jpg", "57");
            assertEquals(4, filePath + "tst_64.png", "64");
            assertEquals(8, filePath + "tst_58.jpg", "58");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void assertEquals(int expected, String imageName, String testNumber) throws IOException {
        ImageMatrix imageMatrix = new ImageMatrix();
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer();
        BufferedImage image = ImageIO.read(new File(imageName));

        Color[][] colors = imageMatrix.createColorMatrix(image);
        imageMatrix.pixelConnectionMaker(colors);
        Node[][] nodes = imageMatrix.getNodes();
        nodes = imageMatrix.blackAndWhiteMaker(nodes);

        Color bgColor = imageAnalyzer.getBgColor(nodes);
        int silNumber = imageAnalyzer.silhouettesCounter(nodes, bgColor);

        if (silNumber == expected) {
            System.out.println(testNumber + " ( V ) Success!! actual number: " + silNumber + "Expected number: " + expected);
        } else {
            System.out.println(testNumber + "( X ) FAIL!!! actual number: " + silNumber + "Expected number: " + expected);
        }
    }
}
