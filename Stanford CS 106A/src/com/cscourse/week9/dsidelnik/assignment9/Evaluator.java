package com.cscourse.week9.dsidelnik.assignment9;

import java.util.*;

/**
 * Evaluates expression
 *
 * <p> Assumes that expression already parsed (spaces are removed,
 * multi numbers and negative numbers already defined) and ready for further calculations</p>
 */
public class Evaluator {

    // Store values of operator priorities, needed to implement post fix calculations algorithm
    private final Map<String, Integer> OPERATOR_PRIORITIES;

    public Evaluator() {
        OPERATOR_PRIORITIES = new HashMap<>();
        operatorMapFiller();
    }

    /**
     * Fills map with priority values
     * should be invoked before calculations
     */
    private void operatorMapFiller() {
        OPERATOR_PRIORITIES.put("^", 1);
        OPERATOR_PRIORITIES.put("*", 2);
        OPERATOR_PRIORITIES.put("/", 2);
        OPERATOR_PRIORITIES.put("+", 3);
        OPERATOR_PRIORITIES.put("-", 3);
    }

    /**
     * Method that composes shunting yard algorithm methods and returns result
     * of calculations
     *
     * @param expression parsed epxression
     * @return solution
     */
    public double calculate(List<String> expression) {
        Stack<String> operators = new Stack<>();
        Stack<String> postfixExpression = new Stack<>();

        shuntingStacks(operators, postfixExpression, expression);

        return shuntingYardSolver(postfixExpression);
    }

    /**
     * turns infix notation expression to the postfix notation expression
     * for further calculations
     *
     * @param operators stack for operators
     * @param numbers   stack for numbers
     * @param exp       parsed expression
     */
    private void shuntingStacks(Stack<String> operators, Stack<String> numbers, List<String> exp) {
        List<String> expression = new ArrayList<>(exp);
        EquationParser parser = new EquationParser();
        String lastSymbol;
        String retrievedSymbol;
        int symbPriorityCurr, symbPriorityPrev;


        for (String str : expression) {

            if (parser.isOperator(str) && operators.isEmpty()) {
                operators.push(str);
                continue;
            }

            if (!parser.isOperator(str)) {
                numbers.push(str);
            }

            if (parser.isOperator(str)) {

                lastSymbol = operators.peek();
                symbPriorityPrev = OPERATOR_PRIORITIES.get(lastSymbol);
                symbPriorityCurr = OPERATOR_PRIORITIES.get(str);

                if (symbPriorityPrev <= symbPriorityCurr) {
                    while (true) {
                        if (operators.isEmpty()) break;

                        lastSymbol = operators.peek();
                        symbPriorityPrev = OPERATOR_PRIORITIES.get(lastSymbol);
                        symbPriorityCurr = OPERATOR_PRIORITIES.get(str);

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
     * Shunting yard algorithm main logic
     *
     * @param postfixExpression already processed postfix epxression
     * @return double values (solution of expression)
     */
    private double shuntingYardSolver(Stack<String> postfixExpression) {
        List<String> expressionReversed;
        EquationParser parser = new EquationParser();

        Stack<String> expressionStack = new Stack<>();
        Stack<String> operationStack = new Stack<>();

        expressionReversed = new ArrayList<>(postfixExpression);
        Collections.reverse(expressionReversed);

        for (String str : expressionReversed) {
            expressionStack.push(str);
        }

        while (!expressionStack.isEmpty()) {
            String currSymbol = expressionStack.peek();

            if (!parser.isOperator(currSymbol)) {
                operationStack.push(expressionStack.pop());
            } else if (parser.isOperator(currSymbol)) {
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
     *
     * @param operators stack which stored generally operators
     * @param numbers   stack which generally stored only numbers
     */
    private void shuntingOpToNumber(Stack<String> operators, Stack<String> numbers) {
        while (!operators.isEmpty()) {
            numbers.push(operators.pop());
        }
    }

    /**
     * Depending of operator performs following math operation (addition, subtraction, multiplication, division,
     * and exponentiation) return following result as String variable
     *
     * @param var1     first operand
     * @param operator operator
     * @param var2     second operand
     * @return result value of math operation as a String variable
     */
    public String mathOperation(String var1, String operator, String var2) throws ArithmeticException {
        double result = 0.0;

        switch (operator) {
            case "+":
                result = Double.parseDouble(var1) + Double.parseDouble(var2);
                break;
            case "-":
                result = Double.parseDouble(var1) - Double.parseDouble(var2);
                break;
            case "/":
                if (Double.parseDouble(var2) != 0) result = Double.parseDouble(var1) / Double.parseDouble(var2);
                else throw new ArithmeticException();
                break;
            case "*":
                result = Double.parseDouble(var1) * Double.parseDouble(var2);
                break;
            case "^":
                result = Math.pow(Double.parseDouble(var1), Double.parseDouble(var2));
        }
        return String.valueOf(result);
    }
}
