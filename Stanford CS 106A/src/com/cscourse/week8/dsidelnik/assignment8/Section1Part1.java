package com.cscourse.week8.dsidelnik.assignment8;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Mini program that gets a directory a searching an image (.jpg) files in it and
 * in every other directory which is in it
 * Uses recursion method to traverse folders
 */
public class Section1Part1 {

    public static void main(String[] args) {
        List<File> fileList = new ArrayList<>();
        searchFiles(new File("D:\\"), fileList);
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * Gets starting file or directory as a parameter and searching for an image files with extension .jpg
     *
     * @param rootFile gets file to start with checks whether it is a directory or file and search for
     *                 image file with .jpg extension, if it is a folder than it gets in to it through
     *                 recursion method and repeats all steps until it is no unvisited folders in
     *                 root directory
     * @param fileList while finding an image files (with extension .jpg) stores it in a list "fileList"
     */
    private static void searchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            System.out.println("Searching at: " + rootFile.getAbsolutePath());

            File[] directoryFiles = rootFile.listFiles();

            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    } else {

                        // checks whether file is with .jpg extension
                        if (file.getName().toLowerCase().endsWith(".jpg")) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
}
