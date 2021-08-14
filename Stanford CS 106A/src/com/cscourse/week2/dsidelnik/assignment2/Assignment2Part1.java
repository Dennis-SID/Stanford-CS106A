package week2.dsidelnik.assignment2;

import com.shpp.cs.a.console.TextProgram;

import java.util.Scanner;

/**
 * Assignment2Part1
 * Console program that gets three numbers as an input
 * and returns roots of square equation
 * EXAMPLE OF SQUARE EQUATION: a * (x ^ 2) + a * x + c = 0
 */
public class Assignment2Part1 extends TextProgram {

    // gets user input
    private final Scanner SCANNER = new Scanner(System.in);

    /**
     * Main action method that starts program
     */
    public void run() {
        double a, b, c;
        System.out.println("Please enter a,b,c coefficients in sequential order \n (a should not be equal to 0)");
        a = SCANNER.nextDouble();
        while(a == 0) a = SCANNER.nextDouble();
        b = SCANNER.nextDouble();
        c = SCANNER.nextDouble();
        defineRoots(a, b, c);
    }

    /**
     * defines roots for square equation
     * @param a coefficient a typed by user (can't be a zero)
     * @param b coefficient b typed by user
     * @param c coefficient c typed by user
     */
    private void defineRoots(double a, double b, double c) {
        double discriminant = (b * b) - (4 * a * c);
        double root1, root2;

        if (discriminant > 0) {
            root1 = plusOperation(discriminant, a, b);
            root2 = minusOperation(discriminant, a, b);
            System.out.println("ROOT1 = " + root1 + "ROOT2 = " + root2);
        } else if (discriminant == 0) {
            root1 = plusOperation(discriminant, a, b);
            System.out.println("ROOT = " + root1);
        } else if (discriminant < 0) {
            System.out.println("NO ROOTS FOR THIS EQUATION");
        }
    }

    /* Helper method for calculations */
    private double minusOperation(double disc, double a, double b){
        return ((-b) - Math.sqrt(disc)) / (2 * a);
    }

    /* Helper method for calculations */
    private double plusOperation(double disc, double a, double b) {
        return ((-b) + Math.sqrt(disc)) / (2 * a);
    }
}
