package com.cscourse.week9.dsidelnik.assignment9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Parse expression
 * Defines single values, also negative and double values and turns it in to list
 * of separate String type variables to further it calculations
 */
public class EquationParser {


    /**
     * Gets math expression represented as String line
     * parse it and prepare for further calculations
     *
     * @param line math expression as text string
     * @return list of tokens to solve math expression
     */
    public List<String> parseExpression(String line) {
        List<String> expression = spaceRemove(line);
        expression = numberDetecting(expression);
        expression = negativeValueFinder(expression);
        return expression;
    }

    /**
     * If user wants to solve the same expression with another variables
     * this method asks user for new input and stores it in to the instance
     * map variable
     */
    public void changeVariables(Map<String, String> varValues) {

        if (varValues != null) {
            for (String str : varValues.keySet()) {
                System.out.print(str + "=");
                varValues.put(str, new Scanner(System.in).nextLine());
                System.out.println();
            }
        }
    }

    /**
     * if args array is greater than 1 length this method parse it and
     * finds variables to substitute it to expression later
     * <p>
     * to store it uses class scope Map variable
     *
     * @param args command line arguments (assumes that there are more than one arguments present)
     */
    public void unknownVariablesParser(String[] args, Map<String, String> varValues) {
        List<String> result;
        StringBuilder sb;
        String letter;

        for (String str : args) {

            if (str != null) {
                result = spaceRemove(str);
                sb = new StringBuilder();

                letter = result.get(0);
                result.subList(0, 2).clear(); // removes two first symbols (always letter and "=" sign)

                if (result.size() > 1) {
                    for (String num : result) sb.append(num);
                    varValues.put(letter, sb.toString());
                } else varValues.put(letter, result.get(0));
            }
        }
    }

    /**
     * If variables are present in expression this method gets them from map and
     * puts the real values to expression
     *
     * @param values expression with variables that needed to be substituted
     * @return string list with real values instead unknown variables
     */
    public List<String> valuesSubstitute(List<String> values, Map<String, String> varValues) {
        List<String> resultList = new ArrayList<>();
        if (values.isEmpty()) return null;

        for (String str : values)
            resultList.add(varValues.getOrDefault(str, str)); // if no such key in the map, leave value without change
        return resultList;
    }

    /**
     * Takes expression as String line
     * divide it in to separate variables and removes spaces
     *
     * @param line string typed line (expression)
     * @return list of String type variables for further calculations
     */
    public List<String> spaceRemove(String line) {
        char[] lineAsChar = line.toCharArray();
        List<String> resultList = new ArrayList<>();

        for (char ch : lineAsChar)
            if (ch != ' ') resultList.add(String.valueOf(ch));
        return resultList;
    }

    /**
     * Method used to find multi numbers in expression
     *
     * @param list list of String type variables, assumes that spaces already removed
     * @return list with separate multi numbers
     */
    public List<String> numberDetecting(List<String> list) {
        List<String> resultList = new ArrayList<>();
        StringBuilder operatorOperand;
        for (int i = 0; i <= list.size() - 1; i++) {
            operatorOperand = new StringBuilder();

            if (isNumber(list.get(i))) {
                while (isNumber(list.get(i))) {
                    operatorOperand.append(list.get(i));
                    i++;
                    if (i == list.size()) break;
                }
                resultList.add(operatorOperand.toString());
            }
            if (i < list.size() - 1) {
                if (isOperator(list.get(i))) {
                    resultList.add(list.get(i));
                }
            }
        }
        return resultList;
    }

    /**
     * defines negative numbers in expression
     *
     * @param expression list of String type variables, assumes that spaces already removed
     *                   and multi numbers are defined
     * @return list with separate negative values
     */
    public List<String> negativeValueFinder(List<String> expression) {
        List<String> resultList = new ArrayList<>();
        StringBuilder symbol;
        boolean operator = true;

        for (int i = 0; i < expression.size(); i++) {
            symbol = new StringBuilder();

            if (operator && isOperator(expression.get(i))) {
                symbol.append(expression.get(i));
                symbol.append(expression.get(++i));
                operator = false;
            } else if (!operator && isOperator(expression.get(i))) {
                symbol.append(expression.get(i));
                operator = true;
            } else {
                symbol.append(expression.get(i));
                operator = false;
            }

            resultList.add(symbol.toString());
        }
        return resultList;
    }

    /**
     * Helper method, used to define whether symbol is number or not
     * Used to distinguish numbers, operators etc.
     *
     * @param symbol String variables which needed to be analyzed
     * @return if number returns true, else false
     */
    private boolean isNumber(String symbol) {
        char[] symbolAsChar = symbol.toCharArray();
        return Character.isDigit(symbolAsChar[0]) || symbolAsChar[0] == '.' || Character.isLetter(symbolAsChar[0]);
    }

    /**
     * Uses isNumber method. Created to make code more user friendly
     *
     * @param symbol String variable to analyze
     * @return if variables is operator returns true, else false
     */
    public boolean isOperator(String symbol) {
        return "+-*/^".contains(symbol);
    }
}
