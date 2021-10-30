package com.cscourse.week11.dsidelnik.assignment11.memoryoptimized;

import java.io.IOException;

public class Assignment11Part1 {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t1, Throwable e1) {
                System.out.println("Your java virtual machine requires more allocated memory for stack storing,");
                System.out.println("you can solve this problem by giving access for JVM for more RAM usage in settings,");
                System.out.println("or choose smaller size image");
            }
        });

        try {
            SilhouettesCounter silhouettesCounter = new SilhouettesCounter();
            int silhouettesNumber = silhouettesCounter.count(args);
            System.out.println("The number of silhouettes on the picture: " + silhouettesNumber);

        } catch (IOException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }
}
