package com.cscourse.week10.dsidelnik.assignment10;

import java.util.*;

public class Evaluator {

    // Store values of operator priorities, needed to implement post fix calculations algorithm
    private Map<String, Integer> operatorPriorities;

    public Evaluator() {
        operatorPriorities = new HashMap<>();
        operatorMapFiller();
    }

    /**
     * Fills map with priority values
     * should be invoked before calculations
     */
    private void operatorMapFiller() {
        operatorPriorities.put("^", 1);
        operatorPriorities.put("*", 2);
        operatorPriorities.put("/", 2);
        operatorPriorities.put("+", 3);
        operatorPriorities.put("-", 3);
    }

    /**
     * Method that composes shunting yard algorithm methods and returns result
     * of calculations
     * @param expression parsed epxression
     * @return solution
     */
    public double calculate (List<String> expression) {
        Stack<String> operators = new Stack<>();
        Stack<String> postfixExpression = new Stack<>();

        shuntingStacks(operators, postfixExpression, expression);

        return shuntingYardSolver(postfixExpression);
    }

    /**
     * turns infix notation expression to the postfix notation expression
     * for further calculations
     * @param operators stack for operators
     * @param numbers stack for numbers
     * @param exp  parsed expression
     */
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

    /**
     * Chunting yard algorith main logic
     * @param postfixExpression already processed postfix epxression
     * @return double values (solution of expression)
     */
    private double shuntingYardSolver(Stack<String> postfixExpression) {
        List<String> expressionReversed;
        Stack<String> expressionStack = new Stack<>();
        Stack<String> operationStack = new Stack<>();

        expressionReversed = new ArrayList<>(postfixExpression);
        Collections.reverse(expressionReversed);

        for (String str : expressionReversed) {
            expressionStack.push(str);
        }


        while(!expressionStack.isEmpty()) {
            String currSymbol = expressionStack.peek();

            if (!isOperator(currSymbol)) {
                operationStack.push(expressionStack.pop());
            } else if (isOperator(currSymbol)) {
                String secondOperand = operationStack.pop();
                String firstOperand = operationStack.pop();
                String operator = expressionStack.pop();
                String result = mathOperation(firstOperand, operator, secondOperand);
                operationStack.push(result);
            }
        }
        return Double.parseDouble(operationStack.peek());
    }

    /**
     * Helper method used, part of shunting yard algorithm implementation,
     * gets two Stack variables and makes manipulations (gets all operators from one stack and puts it to another
     * until first gets empty)
     * gets reference to both stack variables and changes it without return value
     * @param operators stack which stored generally operators
     * @param numbers stack which generally stored only numbers
     */
    private void shuntingOpToNumber(Stack<String> operators, Stack<String> numbers) {
        while (!operators.isEmpty()) {
            String lastOperator = operators.pop();
            numbers.push(lastOperator);
        }
    }

    /**
     * Depending of operator performs following math operation (addition, subtraction, multiplication, division,
     * and exponentiation) return following result as String variable
     * @param var1 first operand
     * @param operator operator
     * @param var2 second operand
     * @return result value of math operation as a String variable
     */
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

    /**
     * Helper method, created to make the code more user friendly
     * @param symbol String carables which needed to be analyzed
     * @return if String variable is operator returns true, else false
     */
    private boolean isOperator(String symbol) {
        List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "^", "/"));
        return operators.contains(symbol);
    }
}
