package com.cscourse.week10.dsidelnik.assignment10;

import java.util.*;

/**
 * Main class that composes all classes and implements functionality of the calculator
 * <p>
 * Gets an expression from the command line and any other variables if they present
 * in expression. Solve expression and return the result on a screen.
 */
public class Assignment10Part1 {

    /**
     * Used to store unknown variables if needed
     */
    public static Map<String, String> unknownValues;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("It is no expression to solve");
            return;
        }

        try {
            EquationParser parser = new EquationParser();
            Evaluator evaluator = new Evaluator();
            Scanner scanner = new Scanner(System.in);

            List<String> expression = parser.parseExpression(args[0]); // parse expression

            // if args has variables parse it, substitute with values and solve expression
            // than asks user for choice (solve this expression with another variables ore stop the program)
            if (args.length > 1) {
                String[] arr = new String[args.length - 1]; // creates new array to search variables there
                System.arraycopy(args, 1, arr, 0, args.length - 1);

                unknownValues = new HashMap<>();
                parser.unknownVariablesParser(arr, unknownValues);

                while (true) {
                    List<String> varExpression = new ArrayList<>(expression);
                    List<String> varSubExpression = parser.valuesSubstitute(varExpression, unknownValues);
                    double result = evaluator.calculate(varSubExpression);

                    System.out.println("Your expression is: " + args[0]);
                    System.out.println(unknownValues);
                    System.out.println("The result of expression will be: " + result);

                    System.out.println("Would you like to continue with another variables? (if yes type \"Y\" letter and press enter)");
                    String choice = scanner.next();
                    if (!choice.equalsIgnoreCase("y")) break;
                    parser.changeVariables(unknownValues);
                }
            } else {
                double result = evaluator.calculate(expression);
                System.out.println("Your expression is: " + args[0]);
                System.out.println("Result of the expression will be: " + result);
            }
        } catch (ArithmeticException e) {
            System.out.println("Zero division is prohibited!");
            e.printStackTrace();
        } catch (IllegalArgumentException arg) {
            System.out.println("Name of function was written wrong or function is not allowed in this version");
            arg.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Something went wrong!!! \n Please check your expression and try again.");
            ex.printStackTrace();
        }
    }
}
