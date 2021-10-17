package com.cscourse.week10.dsidelnik.assignment10;

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
        OPERATOR_PRIORITIES.put("sin", 1);
        OPERATOR_PRIORITIES.put("cos", 1);
        OPERATOR_PRIORITIES.put("tan", 1);
        OPERATOR_PRIORITIES.put("atan", 1);
        OPERATOR_PRIORITIES.put("log10", 1);
        OPERATOR_PRIORITIES.put("log2", 1);
        OPERATOR_PRIORITIES.put("sqrt", 1);
        OPERATOR_PRIORITIES.put("^", 2);
        OPERATOR_PRIORITIES.put("*", 3);
        OPERATOR_PRIORITIES.put("/", 3);
        OPERATOR_PRIORITIES.put("+", 4);
        OPERATOR_PRIORITIES.put("-", 4);
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
        double result = shuntingYardSolver(postfixExpression);
        return result;
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

            if (isNumber(currSymbol)) {
                operationStack.push(expressionStack.pop());
            } else if (parser.isOperator(currSymbol)) {
                String secondOperand = operationStack.pop();
                String firstOperand = operationStack.pop();
                String operator = expressionStack.pop();
                String result = mathOperation(firstOperand, operator, secondOperand);
                operationStack.push(result);
            } else if (isFunction(currSymbol)) {
                String operand = operationStack.pop();
                String function = expressionStack.pop();
                String result = functionOperation(function, operand);
                operationStack.push(result);
            }
        }

        return Double.parseDouble(operationStack.peek());
    }

    /**
     * turns infix notation expression to the postfix notation expression
     * for further calculations
     *
     * @param operators stack for operators
     * @param numbers   stack for numbers
     * @param parsedExpression       parsed expression
     */
    private void shuntingStacks(Stack<String> operators, Stack<String> numbers, List<String> parsedExpression) {
        List<String> expression = new ArrayList<>(parsedExpression);
        EquationParser parser = new EquationParser();

        for (String str : expression) {
            if (operators.isEmpty() && (parser.isOperator(str) || isFunction(str))) {
                operators.push(str);
            } else if (!operators.isEmpty() && (parser.isOperator(str) || isFunction(str)) && !operators.peek().equals("(")) {
                if (OPERATOR_PRIORITIES.get(operators.peek()) <= OPERATOR_PRIORITIES.get(str)) {
                    while (OPERATOR_PRIORITIES.get(operators.peek()) <= OPERATOR_PRIORITIES.get(str)) {
                        numbers.push(operators.pop());
                        if (operators.isEmpty() || operators.peek().equals("(")) {
                            operators.push(str);
                            break;
                        }
                    }
                } else {
                    operators.push(str);
                }
            } else if (!operators.isEmpty() && (parser.isOperator(str) || isFunction(str)) && operators.peek().equals("(")) {
                operators.push(str);
            }else if (!operators.isEmpty() && str.equals("(")) {
                    operators.push(str);
            } else if (str.equals(")")) {
                while(!operators.peek().equals("(")) {
                    numbers.push(operators.pop());
                }
                operators.pop();
            } else {
                numbers.push(str);
            }
        }
        shuntingOpToNumber(operators, numbers);
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

    /**
     * Used to perform function math operation
     * @param function name of the function (cos, sin, tan etc.)
     * @param operand String representation of value
     * @return result of Math expression stored as String variable
     */
    private String functionOperation(String function, String operand) {
        switch (function) {
            case "sin" : return String.valueOf(Math.sin(Double.parseDouble(operand)));
            case "cos" : return String.valueOf(Math.cos(Double.parseDouble(operand)));
            case "tan" : return String.valueOf(Math.tan(Double.parseDouble(operand)));
            case "atan" : return String.valueOf(Math.atan(Double.parseDouble(operand)));
            case "log10" : return String.valueOf(Math.log10(Double.parseDouble(operand)));
            case "log2" : return String.valueOf(Math.log(Double.parseDouble(operand)) / Math.log(2));
            case "sqrt" : return String.valueOf(Math.sqrt(Double.parseDouble(operand)));
            default: throw new IllegalArgumentException();
        }
    }

    /**
     * Helper method, used to define whether token represents math function
     * @param token String type token
     * @return true if String token is function, else false
     */
    private boolean isFunction(String token) {
        return Arrays.asList("sin", "cos", "tan", "atan", "log10", "log2", "sqrt").contains(token);
    }

    /**
     * Helper method to define number in expression
     * @param symbol String type token to analyze
     * @return true if token is number, else false
     */
    private boolean isNumber(String symbol) {
        return symbol.matches("-?\\d+(\\.\\d+)?");
    }

}
