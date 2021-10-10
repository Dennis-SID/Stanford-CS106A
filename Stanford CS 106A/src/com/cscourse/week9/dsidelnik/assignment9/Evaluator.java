package com.cscourse.week9.dsidelnik.assignment9;

import java.util.*;
import java.util.stream.Collectors;

public class Evaluator {

    private Map<String, Integer> operatorPriorities;


    public Evaluator() {
        operatorPriorities = new HashMap<>();
        operatorMapFiller();
    }

    private void operatorMapFiller() {
        operatorPriorities.put("^", 1);
        operatorPriorities.put("*", 2);
        operatorPriorities.put("/", 2);
        operatorPriorities.put("+", 3);
        operatorPriorities.put("-", 3);
    }


    public double calculate (List<String> expression) {
        /*EquationParser parser = new EquationParser();*/
        Stack<String> operators = new Stack<>();
        Stack<String> postfixExpression = new Stack<>();

        /*List<String> expression = parser.parseExpression(formula);*/
        /*expression = varSubst(expression, variables);*/

        shuntingStacks(operators, postfixExpression, expression);

        return shuntingYardSolver(postfixExpression);
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

    private double shuntingYardSolver(Stack<String> postfixExpression) {
        List<String> expressionReversed;
        Stack<String> expressionStack = new Stack<>();
        Stack<String> operationStack = new Stack<>();

        expressionReversed = postfixExpression.stream().collect(Collectors.toList());
        Collections.reverse(expressionReversed);

        for (String str : expressionReversed) {
            expressionStack.push(str);
        }

        System.out.println("Operation stack: " + operationStack);
        System.out.println("postfixExpression stack: " + expressionStack);

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

    private List<String> varSubst(List<String> expression, Map<String, String> variables) {
        List<String> resultList = new ArrayList<>();

        for (String str : expression)
            resultList.add(variables.getOrDefault(str, str));

        return resultList;
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

    private boolean isOperator(String symbol) {
        List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "^", "/"));
        return operators.contains(symbol);
    }

}
