package com.cscourse.week9.dsidelnik.assignment9;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse expression
 * Defines single values, also negative and double values and turns it in to list
 * of separate String type variables to further it calculations
 */
public class EquationParser {


    public List<String> parseExpression(String line) {
        List<String> expression = spaceRemove(line);
        expression = numberDetecting(expression);
        expression = negativeValueFinder(expression);
        return expression;
    }

    /**
     * Takes expression as String line
     * divide it in to separate variables and removes spaces
     * @param line string typed line (expression)
     * @return list of String type variables for further calculations
     */
    public List<String> spaceRemove(String line) {
        char [] lineAsChar = line.toCharArray();
        List<String> resultList = new ArrayList<>();

        for (char ch : lineAsChar)
            if (ch != ' ') resultList.add(String.valueOf(ch));
        return resultList;
    }

    /**
     * Method used to find multi numbers in expression
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
     * @param symbol String variables which needed to be analyzed
     * @return if number returns true, else false
     */
    private boolean isNumber(String symbol) {
        char [] symbolAsChar = symbol.toCharArray();
        return Character.isDigit(symbolAsChar[0]) || symbolAsChar[0] == '.' || Character.isLetter(symbolAsChar[0]);
    }

    /**
     * Uses isNumber method. Created to make code more user friendly
     * @param symbol String variable to analyze
     * @return if variables is operator returns true, else false
     */
    private boolean isOperator(String symbol) {
        return !isNumber(symbol);
    }
}
