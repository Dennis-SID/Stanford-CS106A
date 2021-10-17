package tests.com.week3.dsidelnik.testassignment3;

import com.cscourse.week10.dsidelnik.assignment10.EquationParser;
import com.cscourse.week10.dsidelnik.assignment10.Evaluator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAssignment10Part1 {

    public static Map<String, String> values = new HashMap<>();

    public static void main(String[] args) {

        /*String [] test1 = {"1 + 2 + 3 + 4 + 5 - -1"};
        double test1Result = testMethod(test1);
        System.out.println(test1Result);*/

        String[] test0 = {"1+(2+3*(4+5-sin(45*cos(2))))/7"};
        String[] test1 = {"1+(2+3*(4+5-sin(45*cos(a))))/7", "a  = 2"};
        String[] test2 = {"1.0 + 2.0 + 3.0 - 1.0 + (( 2.0 - 1.0) + 2.0) * 3.0 + cos(1.0)"};
        String[] test3 = {"5.0 + 2.0 * 3.0 - (-1 + -2) + a - b + c", "a = 2", "b = 3", "c = 5"};
        String[] test4 = {"5.0 + 2.0 * 3.0 - (1 + 2) + 2.0 / b * c", "b = 2.0", "c  =  10.0"};
        String[] test5 = {"10.0 + 5.0 / 6.0 + (1 + -2) + (-1.0 * -1.0) + (( 1 + 3) * 2) / 85.0"};
        String[] test6 = {"100.0 + -55.0 / -6.0 + (-1 + -2) + (-1.0 * -1.0) + (( 1 + 3) * 2) / 85.0"};
        String [] test7 = {"2 ^ 3 ^ 2"};
        String [] test8 = {"cos(2)"};
        String [] test9 = {"sqrt(4*4)"};
        String [] test10 = {"sqrt((4*4+9))"};
        String [] test11 = {"2-log2(sin(2))"};
        String [] test12 = {"2-(3*log2(sin(2)))/3"};
        String [] test13 = {"2^3+1"};
        String [] test14 = {"1+2^3+1"};


        assertEquals(testMethod(test0), 5.0902976753383715, "1");
        assertEquals(testMethod(test1), 5.0902976753383715, "2");
        assertEquals(testMethod(test2), 14.54030230586814, "3");
        assertEquals(testMethod(test3), 18, "4");
        assertEquals(testMethod(test4), 11, "5");
        assertEquals(testMethod(test5), 10.927450980392157, "6");
        assertEquals(testMethod(test6), 107.2607843137255, "7");
        assertEquals(testMethod(test7), 512, "8");
        assertEquals(testMethod(test8), -0.4161468365471424, "9");
        assertEquals(testMethod(test9), 4, "10");
        assertEquals(testMethod(test10), 5, "11");
        assertEquals(testMethod(test11), 2.1371758246471546, "12");
        assertEquals(testMethod(test12), 2.1371758246471546, "13");
        assertEquals(testMethod(test13), 9, "14");
        assertEquals(testMethod(test14), 10, "15");


    }

    public static double testMethod(String[] args) {
        EquationParser parser = new EquationParser();
        Evaluator evaluator = new Evaluator();

        if (args.length == 0) {
            return -1.0;
        } else if (args.length == 1) {
            return evaluator.calculate(parser.parseExpression(args[0]));
        } else {
            String[] arr = new String[args.length - 1]; // creates new array to search variables there
            System.arraycopy(args, 1, arr, 0, args.length - 1);

            List<String> expressionToSolve = parser.parseExpression(args[0]);
            parser.unknownVariablesParser(arr, values);
            List<String> subValues = parser.valuesSubstitute(expressionToSolve, values);
            return evaluator.calculate(subValues);
        }
    }

    public static void assertEquals (double result, double expected, String number) {
        if (result == expected)
            System.out.println("( V ) Success! Value: " + result + " Expected: " + expected + "Test number " + number);
        else
            System.out.println("( X ) Fail! Value: " + result + " Expected: " + expected + "Test number " + number);
    }
}
