package com.cscourse.week9.dsidelnik.assignment9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquationParser {


    public List<String> parseExpression(String line) {
        List<String> expression = spaceRemove(line);
        expression = numberDetecting(expression);
        expression = negativeValueFinder(expression);
        return expression;
    }

    public List<String> spaceRemove(String line) {
        char [] lineAsChar = line.toCharArray();
        List<String> resultList = new ArrayList<>();

        for (char ch : lineAsChar)
            if (ch != ' ') resultList.add(String.valueOf(ch));
        return resultList;
    }

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

    private boolean isNumber(String symbol) {
        char [] symbolAsChar = symbol.toCharArray();
        return Character.isDigit(symbolAsChar[0]) || symbolAsChar[0] == '.' || Character.isLetter(symbolAsChar[0]);
    }

    private boolean isOperator(String symbol) {
        return !isNumber(symbol);
    }



    public static void main(String [] args) {
        EquationParser parser = new EquationParser();
        String line = "-199999.0 +   -1+  -4222.21    -3111.98 + -2333.03 - -222.055";
        System.out.println(parser.spaceRemove(line));
        List<String> listToCheck = parser.spaceRemove(line);
        for (String str : listToCheck) System.out.print(str);
        System.out.println("\n");
        List<String> multiNumberDetected = parser.numberDetecting(listToCheck);
        for (String str : multiNumberDetected) System.out.print(str + " ");
        System.out.println("");
        List<String> negativeNumbers = parser.negativeValueFinder(multiNumberDetected);
        for (String str : negativeNumbers) System.out.print(str + " ");
    }
}
