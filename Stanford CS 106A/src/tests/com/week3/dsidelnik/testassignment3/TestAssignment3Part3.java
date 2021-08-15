package tests.com.week3.dsidelnik.testassignment3;

import com.cscourse.week3.dsidelnik.assignment3.Assignment3Part3;

public class TestAssignment3Part3 {


    public static void main(String[] args) {
        TestAssignment3Part3 test = new TestAssignment3Part3();
        test.testAssignment3Part3();
    }


    private void testAssignment3Part3() {
        Assignment3Part3 a3p3 = new Assignment3Part3();
        assertEquals(Math.pow(1, 0), a3p3.raiseToPower(1, 0));
        assertEquals(Math.pow(2, 0), a3p3.raiseToPower(2, 0));
        assertEquals(Math.pow(3, 1), a3p3.raiseToPower(3, 1));
        assertEquals(Math.pow(3, 2), a3p3.raiseToPower(3, 2));
        assertEquals(Math.pow(0, -1), a3p3.raiseToPower(0, -1));
        assertEquals(Math.pow(0, -2), a3p3.raiseToPower(0, -2));
        assertEquals(Math.pow(0.5, -1), a3p3.raiseToPower(0.5, -1));
        assertEquals(Math.pow(0.5, -2), a3p3.raiseToPower(0.5, -2));
        assertEquals(Math.pow(0.5, -3), a3p3.raiseToPower(0.5, -3));
        assertEquals(Math.pow(1, -3), a3p3.raiseToPower(1, -3));
        assertEquals(Math.pow(1.2, -3), a3p3.raiseToPower(1.2, -3));
        assertEquals(Math.pow(1.2, -3), a3p3.raiseToPower(1.2, -3));
        assertEquals(Math.pow(-1, -3), a3p3.raiseToPower(-1, -3));
        assertEquals(Math.pow(-1.5, -3), a3p3.raiseToPower(-1.5, -3));
        assertEquals(Math.pow(-3, -3), a3p3.raiseToPower(-3, -3));
        assertEquals(Math.pow(-3, 0), a3p3.raiseToPower(-3, 0));
        assertEquals(Math.pow(-3, 1), a3p3.raiseToPower(-3, 1));
        assertEquals(Math.pow(-3, 2), a3p3.raiseToPower(-3, 2));
        assertEquals(Math.pow(-3, 10), a3p3.raiseToPower(-3, 10));
        assertEquals(Math.pow(-3, 11), a3p3.raiseToPower(-3, 11));
        assertEquals(Math.pow(-3.22, 11), a3p3.raiseToPower(-3.22, 11));
        assertEquals(Math.pow(-0, +0), a3p3.raiseToPower(-0, +0));
        assertEquals(Math.pow(-0, 1), a3p3.raiseToPower(-0, 1));
    }

    private void assertEquals(double expected, double actual) {
        String result = "Expected result is: " + expected + ";  The actual value is: " + actual;
        if (expected == actual) System.out.print(" ( V ) " + result + "   SUCCESS ( V )!\n");
        else System.out.print(" ( X ) " + result + "    FAIL! ( X )\n");
    }

}
