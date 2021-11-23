package com.cscourse.week13.dsidelnik.assignment13;

public class Assignment13Part1 {

    private String inputName;
    private String outputName;

    private static final String FLAG_ARCHIVE = "-a";
    private static final String FLAG_UNARCHIVE = "-u";
    private static final String EXTENSION = ".par";
    private static final String EXTENSION_UNARCHIVED = ".uar";



    public static void main(String [] args) {
        Assignment13Part1 archiver = new Assignment13Part1();

        System.out.println(archiver.removeExtension("test.txt") + EXTENSION);


    }

    private void checkArguments(String [] args) {
        if (args.length == 0) {
            inputName = "test.txt";
            outputName = removeExtension(inputName) + EXTENSION;
        } else if (args.length == 1) {
            if (isArchive(args[0])) {
                inputName = args[0];
                outputName = removeExtension(args[0]) + EXTENSION_UNARCHIVED;
            } else {
                inputName = args[0];
                outputName = removeExtension((args[0])) + EXTENSION;
            }
        } else if (args.length == 2) {

        }

    }

    private String removeExtension(String line) {
        return line.substring(0, line.indexOf("."));
    }

    private boolean isArchive(String line) {
        return line.contains(EXTENSION);
    }

    private void output(String [] args) {
        System.out.println("Start compression\n");
        System.out.println("File size: " + "\n");
        System.out.println("Archived file size: " + "\n");
        System.out.println("Compression efficiency: " + "\n");
        System.out.println("Work time: " + "\n");
    }
}
