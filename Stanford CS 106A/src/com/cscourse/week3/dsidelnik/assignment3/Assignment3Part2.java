package com.cscourse.week3.dsidelnik.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Scanner;

/**
 * Assignment3 Part2
 * Hailstone problem
 * <p>Program takes some non negative integer
 * if integer is even than divide it on a two, if integer is odd then multiply on three
 * and add one. Continue that process until integer will be equal one</p>
 */
public class Assignment3Part2 extends TextProgram {

    /**
     * Main action method that runs the program
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int number;

        System.out.println("Enter a number:");
        number = scanner.nextInt();

        processNumber(number);
    }

    /**
     * Takes number and process it
     * While number is not equal 1 divides it if it is even
     * and multiply it on three and add one if odd
     *
     * @param number non negative integer to process
     */
    private void processNumber(int number) {
        int processNumber = number;
        while (processNumber != 1) {
            if (processNumber % 2 == 0) {
                System.out.println(processNumber + " is even so I take half: " + processNumber / 2);
                processNumber /= 2;
            } else {
                System.out.println(processNumber + " is odd so I make 3n + 1: " + (3 * processNumber + 1));
                processNumber = 3 * processNumber + 1;
            }
        }
    }
}
