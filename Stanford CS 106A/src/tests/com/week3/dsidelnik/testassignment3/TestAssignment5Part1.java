package tests.com.week3.dsidelnik.testassignment3;

import com.cscourse.week5.dsidelnik.assignment5.Assignment5Part1;

public class TestAssignment5Part1 {

    public static void main (String [] args) {
        TestAssignment5Part1 test  = new TestAssignment5Part1();
        test.myTest(3, "Unity");
        test.myTest(2, "Unite");
        test.myTest(3, "UniIiteEe");
        test.myTest(1, "Growth");
        test.myTest(5, "Possibilities");
        test.myTest(1, "Nimble");
        test.myTest(1, "Me");
        test.myTest(3, "Beautiful");
        test.myTest(3, "Manatee");
        test.myTest(1, "MeeeEEEeeee");
        test.myTest(1, "AAAaaa");
        test.myTest(1, "A");
        test.myTest(1, "a");
        test.myTest(1, "aEeE");
    }

    private void myTest(int expected, String word) {
        Assignment5Part1 a5p1 = new Assignment5Part1();
        int result = a5p1.syllablesIn(word);
        if (result == expected) {
            System.out.println("( V ) SUCCESS! word: " + word + " expected: " + expected + " result: " + result);
        } else {
            System.out.println("( X ) FAIL! word: " + word + " expected: " + expected + " result: " + result);
        }
    }
}
