package com.cscourse.week9.dsidelnik.assignment9;

import java.util.*;

public class Assignment9Part1 {

    private Map<String, String> unknownValues;

    public static void main(String [] args) {

        Assignment9Part1 assignment = new Assignment9Part1();
        EquationParser parser = new EquationParser();

        List<String> expression = parser.parseExpression(args[0]);

        if (args.length > 1) {

            String [] arr = new String[args.length - 1];
            System.arraycopy(args, 1, arr, 0, args.length - 1);
            assignment.unknownVariablesParser(arr);
        }

        for (String str : assignment.unknownValues.keySet()) System.out.println("Key: " + str + "Value: " +
                assignment.unknownValues.get(str));

        System.out.println(assignment.unknownValues);
        System.out.println(expression);

        List<String> resultList = assignment.valuesSubstitute(expression);
        System.out.println(resultList);
    }

    public void unknownVariablesParser(String [] args) {

        EquationParser parser = new EquationParser();
        unknownValues = new HashMap<>();
        List<String> result;
        StringBuilder sb;
        String letter;

        for (String str : args) {

            if (str != null) {
                result = parser.spaceRemove(str);
                sb = new StringBuilder();

                letter = result.get(0);
                result.subList(0, 2).clear();

                if (result.size() > 1) {
                    for (String num : result) sb.append(num);
                    unknownValues.put(letter, sb.toString());
                } else unknownValues.put(letter, result.get(0));
            }
        }
    }

    public List<String> valuesSubstitute(List<String> values) {
        List<String> resultList = new ArrayList<>();

        for (String str : values)
            resultList.add(unknownValues.getOrDefault(str, str));

        return resultList;
    }


}
