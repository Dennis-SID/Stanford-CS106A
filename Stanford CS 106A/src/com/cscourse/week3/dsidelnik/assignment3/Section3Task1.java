package com.cscourse.week3.dsidelnik.assignment3;

import java.util.Scanner;

/**
 * Section3 Task1
 * Class that calculates amount of money you'll receive from your deposit
 * bank account depending on how many years and how much money you will put each year.
 */
public class Section3Task1 {

    // set interest on a deposit
    private static final double ANNUAL_CREDIT_RATE = 7.5;

    // variables assigned through user input
    private double retireYear;
    private double startSaving;
    private double saveAmount;

    public static void main (String [] args) {
        Section3Task1 s3t1 = new Section3Task1();
        s3t1.getUserInput();
        s3t1.cashCounter();
    }

    /*
     * Uses Scanner class to get user input
     * to initialize instance variables
     * to get relevant result should be invoked before cashCounter() method
     */
    private void getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What year do you plan to retire?");
        retireYear = scanner.nextDouble();

        System.out.println("What year do you plan to start saving?");
        startSaving = scanner.nextDouble();

        System.out.println("How much $ per year do you plan to save?");
        saveAmount = scanner.nextDouble();
    }

    /*
     * Counts sum on deposit depending on entered parameters
     * assumes that all instance variables are assigned
     * if instance variables are not assigned with values default values will be zero
     * thus method will print zero in that case
     */
    private void cashCounter() {
        int counter = 0;
        double result = saveAmount;

        while (counter < retireYear - startSaving) {
            result += (result * ANNUAL_CREDIT_RATE / 100) + saveAmount; // counts deposit percents and adds them to base sum
            counter++;
        }
        result -= saveAmount; // subtracts save amount of last year
        System.out.println("In " + retireYear + ", you'd have around $" + result);
    }
}
