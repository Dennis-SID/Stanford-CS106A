package com.cscourse.week12.dsidelnik.assignment12;

import java.io.IOException;


/**
 * Assignment12Part1
 * Silhouettes counter using breadth first search algorithm
 */
public class Assignment12Part1 {

    public static void main(String[] args) {

        // handles stack overflow error and other errors which may occur at a runtime
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
             System.out.println("Something went wrong, most probably your JVM needs more RAM to process the image");
            }
        });

        try {
            SilhouettesCounter silhouettesCounter = new SilhouettesCounter();
            String fileName = "assets/test.jpg";
            if (args.length > 0) fileName = args[0];
            System.out.println("The number of silhouettes in the picture: " + silhouettesCounter.count(fileName));
        } catch (IOException e) {
            System.out.println("The file is does not exist or the path to file is wrong");
            e.printStackTrace();
        }
    }

    // for test purposes
    public int testSil(String [] args) {

        try {
            SilhouettesCounter silhouettesCounter = new SilhouettesCounter();
            String fileName = "assets/test.jpg";
            if (args.length > 0) fileName = args[0];
            return silhouettesCounter.count(fileName);
        } catch (IOException e) {
            System.out.println("The file is does not exist or the path to file is wrong");
            e.printStackTrace();
        }
        return 0;
    }
}
