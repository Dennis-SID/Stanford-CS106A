package com.cscourse.week9.dsidelnik.assignment9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Evaluator {

    public double calculate (String formula, Map<String, String> variables) {
        EquationParser parser = new EquationParser();

        List<String> expression = parser.parseExpression(formula);
        expression = varSubst(expression, variables);

        /* TODO */
        return 0.0;
    }

    private List<String> varSubst(List<String> expression, Map<String, String> variables) {
        List<String> resultList = new ArrayList<>();

        for (String str : expression)
            resultList.add(variables.getOrDefault(str, str));

        return resultList;
    }

    private String mathOperation(String var1, String operator, String var2) {
        double doubleVar1 = Double.parseDouble(var1);
        double doubleVar2 = Double.parseDouble(var2);
        double result = 0.0;

        switch(operator) {
            case "+" : result = doubleVar1 + doubleVar2;
            case "-" : result = doubleVar1 - doubleVar2;
            case "/" :  if (doubleVar2 > 0) result = doubleVar1 / doubleVar2;
                        else result = 0.0;
            case "*" : result = doubleVar1 * doubleVar2;
            case "^" : result = Math.pow(doubleVar1, doubleVar2);
        }
        return String.valueOf(result);
    }

}
