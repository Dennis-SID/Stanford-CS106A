package com.cscourse.week3.dsidelnik.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Scanner;

/**
 * <strong>Assignment3 Part3</strong>
 * <p>Returns the value of the first argument  raised to hte power of the second argument
 * special cases:
 * - if the second argument is positive or negative zero, then the result is 1.0;
 * - if the second argument is 1.0 than the result is the same as first argument</p>
 * - second parameter could be a negative value
 *
 * to make method less complex all that exponents to a zero power will equal to one
 * exponent is int type - established by conditions of the task
 */
public class Assignment3Part3 extends TextProgram {

    /**
     * Main action method provided by TextProgram class
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        double baseNumber;
        int power;
        String userInput;

        boolean session = true;
        while(session) {
            System.out.println("Please enter base number: ");
            baseNumber = scanner.nextDouble();  // gets base number from user

            System.out.println("Please enter exponent (power): ");
            power = scanner.nextInt();  // gets exponent (power) from user

            System.out.println(baseNumber + " ^ " + power + " = " + raiseToPower(baseNumber, power));

            System.out.println("If you would like to end session enter \"Y\"  otherwise enter any symbol");
            userInput = scanner.next(); // check whether continue session or break it
            if (userInput.equalsIgnoreCase("y")) {
                session = false;
            }
        }
    }

    /**
     * Raises base number to exponent
     * @param base base number which needs to be raised to power
     * @param exp exponent (power)
     * @return result of exponentiation
     */
    public double raiseToPower(double base, int exp) {
        double result = base;
        int exponent = (exp < 0) ? -exp : exp; // check if exponent is negative
        if (exp == 0) return 1; // if exponent is 0 method returns 1

        for (int i = 1; i < exponent; i++) {
            result = result * base;
        }
        return exp < 0 ? 1 / result : result;
    }
}
