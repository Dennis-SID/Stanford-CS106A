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
 */
public class Assignment3Part3 extends TextProgram {

    public void run() {
        int n;
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
        }
        System.out.println(n = n < 0 ? -n : n);
    }


}
