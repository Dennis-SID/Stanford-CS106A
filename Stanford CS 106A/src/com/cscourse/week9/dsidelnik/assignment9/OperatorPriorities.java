package com.cscourse.week9.dsidelnik.assignment9;

import java.util.Arrays;
import java.util.Map;

public enum OperatorPriorities {

    EXP("^", 1),
    MULT("*", 2),
    DIV("/", 2),
    ADD("+", 3),
    SUB("-", 3);


    private String operatorName;
    private int priority;
    Map<String, Integer> operatorPriority;

    public String getName() {
        return operatorName;
    }

    public int getPriority() {
        return priority;
    }




    public int getPriority(String operatorName) {
        switch (operatorName) {
            case ("^") : return EXP.getPriority();
            case ("*") : return MULT.getPriority();
            case("/") : return DIV.getPriority();
            case ("+") : return ADD.getPriority();
            case ("-") : return SUB.getPriority();
        }
        return -1;
    }

    private void mapFiller() {
        for (OperatorPriorities pr : OperatorPriorities.values()) {
            operatorPriority.put(pr.getName(), pr.getPriority());
        }
    }

    OperatorPriorities() {
        mapFiller();
    }

    OperatorPriorities(String operatorName, int priority) {
        this.operatorName = operatorName;
        this.priority = priority;
        mapFiller();
    }
}
