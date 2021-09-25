package com.cscourse.week9.dsidelnik.assignment9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Assignment9Part1 {

    public static void main(String [] args) {
        String forTest = "123456789+1+22-34";
        Assignment9Part1 a9p1 = new Assignment9Part1();
        System.out.println(a9p1.lineParser(forTest));
    }

    private double evaluator(String expression) {

        return 0.0;
    }

    private List<String> lineParser(String line) {
        char[] symbolsSeparated = line.toCharArray();
        List<String> resultList = new ArrayList<>();


        for (int i = 0; i < symbolsSeparated.length - 1; ) {
            String currNumber = "";
            if (Character.isDigit(symbolsSeparated[i])) {
                while (Character.isDigit(symbolsSeparated[i]) && i < symbolsSeparated.length - 1) {
                    currNumber += symbolsSeparated[i];
                    i++;
                }
                resultList.add(currNumber);
            } else {
                resultList.add("" + symbolsSeparated[i]);
                i++;
            }
        }
        return resultList;
    }
}
