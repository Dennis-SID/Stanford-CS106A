package com.cscourse.week9.dsidelnik.assignment9;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SandBox extends WindowProgram {
    Map<String, Integer> operatorPriorities;

    SandBox() {
        operatorPriorities = new HashMap<>();
        opPrFiller();
    }

    private void opPrFiller() {
        operatorPriorities.put("^", 1);
        operatorPriorities.put("*", 2);
        operatorPriorities.put("/", 2);
        operatorPriorities.put("+", 3);
        operatorPriorities.put("-", 3);
    }



    public static void main (String [] args) {
        SandBox sandBox = new SandBox();

        List<String> expression = Arrays.asList("1", "+", "2", "-", "3", "*", "4", "/", "5", "^", "6");
        System.out.println(expression);

        // creating stacks and shuffle it according to the first part of the algorithm
        Stack<String> operators = new Stack<>();
        Stack<String> numbers = new Stack<>();

        sandBox.shuntingStacks(operators, numbers, expression);
        System.out.println("shuntingStacks method implemented");
        System.out.println("operators stack: " + operators);
        System.out.println("numbers stack: " + numbers);

        sandBox.shuntingYardSolver(numbers);
    }

    private void shuntingStacks(Stack<String> operators, Stack<String> numbers, List<String> exp) {
        List<String> expression = new ArrayList<>(exp);
        String lastSymbol;
        String retrievedSymbol;
        int symbPriorityCurr, symbPriorityPrev;


        for (String str : expression) {

            if (isOperator(str) && operators.isEmpty()) {
                operators.push(str);
                continue;
            }

            if (!isOperator(str)) {
                numbers.push(str);
            }

            if (isOperator(str)) {

                lastSymbol = operators.peek();
                symbPriorityPrev = operatorPriorities.get(lastSymbol);
                symbPriorityCurr = operatorPriorities.get(str);

                if (symbPriorityPrev <= symbPriorityCurr) {
                    while (true) {
                        if (operators.isEmpty()) break;

                        lastSymbol = operators.peek();
                        symbPriorityPrev = operatorPriorities.get(lastSymbol);
                        symbPriorityCurr = operatorPriorities.get(str);

                        if (symbPriorityPrev <= symbPriorityCurr) {
                            retrievedSymbol = operators.pop();
                            numbers.push(retrievedSymbol);
                        } else break;
                    }
                }
                    operators.push(str);
            }


        }
        shuntingOpToNumber(operators, numbers);
    }

    private double shuntingYardSolver(Stack<String> expression) {
        List<String> expressionReversed;
        Stack<String> expressionStack = new Stack<>();
        Stack<String> operationStack = new Stack<>();

        expressionReversed = expression.stream().collect(Collectors.toList());
        Collections.reverse(expressionReversed);

        for (String str : expressionReversed) {
            expressionStack.push(str);
        }

        System.out.println("Operation stack: " + operationStack);
        System.out.println("expression stack: " + expressionStack);

        while(!expressionStack.isEmpty()) {
            System.out.println("loop started: ");
            String currSymbol = expressionStack.peek();
            System.out.println("Current symbol: " + currSymbol);

            if (!isOperator(currSymbol)) {
                operationStack.push(expressionStack.pop());
            } else if (isOperator(currSymbol)) {
                String secondOperand = operationStack.pop();
                String firstOperand = operationStack.pop();
                String operator = expressionStack.pop();
                String result = mathOperation(firstOperand, operator, secondOperand);
                System.out.println("first operand: " + firstOperand);
                System.out.println("second operand: " + secondOperand);
                System.out.println("Operator: " + operator);
                System.out.println("Result: " + result);

                operationStack.push(result);
            }
            System.out.println("Operation stack: " + operationStack);
            System.out.println("Expression stack: " + expressionStack);

        }

        System.out.println("Operation stack: " + operationStack);
        System.out.println("Expression stack: " + expressionStack);


        return Double.parseDouble(operationStack.peek());
    }

    private void shuntingOpToNumber(Stack<String> operators, Stack<String> numbers) {
        while (!operators.isEmpty()) {
            String lastOperator = operators.pop();
            numbers.push(lastOperator);
        }
    }

    private boolean isOperator(String symbol) {
        List<String> operators = Arrays.asList("*", "^", "/", "+", "-");
        return operators.contains(symbol);
    }

    public String mathOperation(String var1, String operator, String var2) {
        double doubleVar1 = Double.parseDouble(var1);
        double doubleVar2 = Double.parseDouble(var2);
        double result = 0.0;

        switch(operator) {
            case "+" : result = doubleVar1 + doubleVar2;
                       break;
            case "-" : result = doubleVar1 - doubleVar2;
                       break;
            case "/" :  if (doubleVar2 > 0) result = doubleVar1 / doubleVar2;
            else result = 0.0;
                       break;
            case "*" : result = doubleVar1 * doubleVar2;
                       break;
            case "^" : result = Math.pow(doubleVar1, doubleVar2);
        }
        return String.valueOf(result);
    }
}
