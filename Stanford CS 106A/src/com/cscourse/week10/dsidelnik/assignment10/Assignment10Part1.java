package com.cscourse.week10.dsidelnik.assignment10;

import java.util.*;

public class Assignment10Part1 {

    private Map<String, String> unknownValues;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("It is no expression to solve");
            return;
        }

        Assignment10Part1 assignment = new Assignment10Part1();
        EquationParser parser = new EquationParser();
        Evaluator evaluator = new Evaluator();
        Scanner scanner = new Scanner(System.in);

        List<String> expression = parser.parseExpression(args[0]); // parse expression

        // if args has variables parse it, substitute with values and solve expression
        // than asks user for choice (solve this expression with another variables ore stop the program)
        if (args.length > 1) {
            String[] arr = new String[args.length - 1];
            System.arraycopy(args, 1, arr, 0, args.length - 1);
            assignment.unknownVariablesParser(arr);
            while (true) {
                List<String> varExpression = new ArrayList<>(expression);
                List<String> varSubExpression = assignment.valuesSubstitute(varExpression);
                double result = evaluator.calculate(varSubExpression);

                System.out.println("Your expression is: " + args[0]);
                System.out.println(assignment.unknownValues);
                System.out.println("The result of expression will be: " + result);

                System.out.println("Would you like to continue with another variables? (if yes type \"Y\" letter and press enter)");
                String choice = scanner.next();
                if (!choice.equalsIgnoreCase("y")) break;
                assignment.changeVariables();
            }
        } else {
            double result = evaluator.calculate(expression);
            System.out.println("Your expression is: " + args[0]);
            System.out.println("Result of the expression will be: " + result);
        }
    }

    /**
     * If user wants to solve the same expression with another variables
     * this method asks user for new input and stores it in to the instance
     * map variable
     */
    public void changeVariables() {
        Scanner scanner = new Scanner(System.in);

        if (unknownValues != null) {
            for (String str : unknownValues.keySet()) {
                System.out.print(str + "=");
                String value = scanner.nextLine();
                unknownValues.put(str, value);
                System.out.println();
            }
        }
    }


    /**
     * if args array is greater than 1 length this method parse it and
     * finds variables to substitute it to expression later
     *
     * to store it uses class scope Map variable
     * @param args command line arguments (assumes that there are more than one arguments present)
     */
    public void unknownVariablesParser(String[] args) {

        if (args.length < 2) return;

        EquationParser parser = new EquationParser();
        unknownValues = new HashMap<>();
        List<String> result;
        StringBuilder sb;
        String letter;

        for (String str : args) {

            if (str != null) {
                result = parser.spaceRemove(str);
                sb = new StringBuilder();

                letter = result.get(0);
                result.subList(0, 2).clear(); // removes two first symbols (always letter and "=" sign)

                if (result.size() > 1) {
                    for (String num : result) sb.append(num);
                    unknownValues.put(letter, sb.toString());
                } else unknownValues.put(letter, result.get(0));
            }
        }
    }

    /**
     * If variables are present in expression this method gets them from map and
     * puts the real values to expression
     * @param values expression with variables that needed to be substituted
     * @return string list with real values instead unknown variables
     */
    public List<String> valuesSubstitute(List<String> values) {
        List<String> resultList = new ArrayList<>();
        if (values.isEmpty()) return null;

        for (String str : values)
            resultList.add(unknownValues.getOrDefault(str, str)); // if no such key in the map, leave value without change

        return resultList;
    }
}
