package com.cscourse.week9.dsidelnik.assignment9;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class SandBox extends WindowProgram {
    public static void main (String [] args) {
        /*List<String> testList = new ArrayList<>();
        testList.add("-");
        testList.add("55.0");
        testList.add("+");
        testList.add("22.0");
        testList.add("-");
        testList.add("11.00");
        testList.add("-");
        testList.add("-");
        testList.add("33.0");

        SandBox sandBox = new SandBox();

        sandBox.negativeValueFinder(testList);
        System.out.println(sandBox.negativeValueFinder(testList));*/
        SandBox sandBox = new SandBox();

       List<String> list = Arrays.asList("1", "+", "1");
       System.out.println(sandBox.numberDetecting(list));

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


    public Map<String, String> unknownValues;

    public void unknownVariablesParser(String [] args) {
        EquationParser parser = new EquationParser();
        unknownValues = new HashMap<String, String>();
        List<String> result;
        List<String> number;

        for (String str : args) {
            result = parser.spaceRemove((str));
            StringBuilder sb = new StringBuilder();

            number = new ArrayList<>(result);
            number.remove(0);
            number.remove(0);

            if (number.size() > 1) {
                for (String num : number) sb.append(num);
                unknownValues.put(result.get(0), sb.toString());
            } else unknownValues.put(result.get(0), number.get(0));
        }
    }

    public List<String> valuesSubstitute(List<String> values) {
        List<String> resultList = new ArrayList<>();

        for (String str : values) {
            if (unknownValues.containsKey(str)) {
                resultList.add(unknownValues.get(str));
            } else {
                resultList.add(str);
            }
        }
        return resultList;
    }

    public java.util.List<String> negativeValueFinder(java.util.List<String> expression) {
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
        return Character.isDigit(symbolAsChar[0]) || symbolAsChar[0] == '.';
    }

    private boolean isOperator(String symbol) {
        return !isNumber(symbol);
    }
}
