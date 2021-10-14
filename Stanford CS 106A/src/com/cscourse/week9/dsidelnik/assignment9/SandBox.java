package com.cscourse.week9.dsidelnik.assignment9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SandBox {

    public static void main (String [] args) {
        String testString = "*";
        long startTime = System.nanoTime();
        boolean a = isOperator(testString);
        long currTime = System.nanoTime();
        System.out.println(currTime - startTime);


        startTime = System.nanoTime();
        a = isOperatorSimple(testString);
        currTime = System.nanoTime();
        System.out.println(currTime - startTime);
    }

    private static boolean isOperator(String symbol) {
        List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "^", "/"));
        return operators.contains(symbol);
    }

    private static boolean isOperatorSimple(String symbol) {
        return "+-*/^".contains(symbol);
    }

}
