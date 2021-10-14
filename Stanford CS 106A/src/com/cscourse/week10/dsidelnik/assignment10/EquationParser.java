package com.cscourse.week10.dsidelnik.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquationParser {

    public List<String> parseExpression(String line) {
        List<String> expression = spaceRemove(line);
        System.out.println("Expression after space remove:   " + expression);
        expression = comaToDot(expression);
        System.out.println("Expression after coma to dot changes:   " + expression);
        expression = numberDetecting(expression);
        System.out.println("Expression after multinumbers detected:   " + expression);
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
        String withoutSpaces = line.replaceAll(" ", "");
        String [] tokens = withoutSpaces.split("");
        return Arrays.asList(tokens);
    }

    public List<String> comaToDot(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (String token : list) {
            if (token.equals(",")) resultList.add(".");
            else resultList.add(token);
        }
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
            if (i < list.size()) {
                if (isOperator(list.get(i)) || isParenthesis(list.get(i))) {
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
        boolean prevIsOperator = true;
        StringBuilder token;

        for (int i = 0; i < expression.size(); i++) {
            token = new StringBuilder();
            if (prevIsOperator && isOperator(expression.get(i))) {
                token.append(expression.get(i));
                token.append(expression.get(++i));
                prevIsOperator = false;
            } else if (!prevIsOperator && isOperator(expression.get(i))) {
                token.append(expression.get(i));
                prevIsOperator = true;
            } else if (expression.get(i).equals("(")) {
                token.append(expression.get(i));
                prevIsOperator = true;
            } else {
                token.append(expression.get(i));
                prevIsOperator = false;
            }
            resultList.add(token.toString());
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
        List<String> operators = Arrays.asList("+", "-", "/", "^", "*");
        return operators.contains(symbol);
    }

    private boolean isParenthesis(String symbol) {
        return symbol.equals(")") || symbol.equals("(");
    }
}
