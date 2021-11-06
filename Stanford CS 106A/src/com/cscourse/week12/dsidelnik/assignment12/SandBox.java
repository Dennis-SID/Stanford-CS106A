package com.cscourse.week12.dsidelnik.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class SandBox {

    public static void main (String [] args) throws IOException {
        BufferedImage image = ImageIO.read( new File("assets/imgs/test.jpg"));

        ColorModel model = image.getColorModel();
        Graphics graphics = image.getGraphics();
        Point[] point = image.getWritableTileIndices();
        Graphics2D graphics2d = image.createGraphics();

        model.hasAlpha();
    }
}
