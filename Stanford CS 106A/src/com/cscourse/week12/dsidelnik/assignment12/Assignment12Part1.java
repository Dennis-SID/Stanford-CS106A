package com.cscourse.week12.dsidelnik.assignment12;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Assignment12Part1 {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
             System.out.println("Something went wrong, probably your program need more RAM");
            }
        });

        try {
            SilhouettesCounter silhouettesCounter = new SilhouettesCounter();
            String fileName = "assets/imgs/tst_25.jpg";
            if (args.length > 0) fileName = args[0];
            System.out.println("The number of silhouettes in the picture: " + silhouettesCounter.count(fileName));
        } catch (IOException e) {
            System.out.println("The file if does not exist or the path to file is wrong");
            e.printStackTrace();
        }
    }
}
