package com.cscourse.week9.dsidelnik.assignment9;

public enum OperatorPriorities {

    EXP("^", 1),
    MULT("*", 2),
    DIV("/", 2),
    ADD("+", 3),
    SUB("-", 3);


    private String operatorName;
    private int priority;

    public String getName() {
        return operatorName;
    }

    public int getPriority() {
        return priority;
    }

    OperatorPriorities(String operatorName, int priority) {
        this.operatorName = operatorName;
        this.priority = priority;
    }
}
