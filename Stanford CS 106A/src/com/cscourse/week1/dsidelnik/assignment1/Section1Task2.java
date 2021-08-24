package com.cscourse.week1.dsidelnik.assignment1;

import java.util.Scanner;

/**
 * Section1 Task2
 * Fizz Bazz Buzz game
 */
public class Section1Task2 {

    /**
     * Main method (starter point of the program)
     * @param arggs command line arguments
     */
    public static void main (String [] arggs) {
        Section1Task2 s1t2 = new Section1Task2();
        s1t2.gameLogic(s1t2.getUserInput());
    }

    /*
     * Asks user for number
     */
    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please range for game: ");
        int result = scanner.nextInt();
        return result;
    }

    /*
     * Provides main logic of the game
     */
    private void gameLogic(int limit) {
        int currNumber = 0;
        while (currNumber < limit) {
            if (currNumber % 3 == 0 && currNumber % 5 == 0) {
                System.out.println("Buzz");
            } else if (currNumber % 3 == 0) {
                System.out.println("Fizz");
            } else if (currNumber % 5 == 0) {
                System.out.println("Bazz");
            } else {
                System.out.println(currNumber);
            }
            currNumber++;
        }
    }
}
