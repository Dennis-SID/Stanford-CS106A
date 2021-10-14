package com.cscourse.week10.dsidelnik.assignment10;

import java.util.List;

public class SandBox {

    public static void main(String [] args) {
        EquationParser parser = new EquationParser();

        String test1 = "1+(20+30)-25+1*2^2+34";
        String test2 = "-1 + 100 + (5 * 5^2) + cos(30) - 21";
        String test3 = "-23,0 + 11 / 2 + (21 + 23 - 11) + (2^2) - sin(5 + 5)";
        String test4 = "-100.0 - 50,0 + (100 / 2) + (10 + 5^2) + (5 + 3^3) - 10 + tan(10)";
        String test5 = "1+(2+3*(4+5-sin(45*cos(2))))/7";
        String test6 = "-1+(-2+-3*(-4+-5-sin(-45*cos(-2))))/7";

        String [] testStrings = {test1, test2, test3, test4, test5, test6};
        int counter = 0;

        for (String str : testStrings) {
            counter++;
            List<String> testString = parser.parseExpression(str);
            System.out.println("String test" + counter + "   " + testString);
            System.out.println("\n \n \n");
        }
    }
}
