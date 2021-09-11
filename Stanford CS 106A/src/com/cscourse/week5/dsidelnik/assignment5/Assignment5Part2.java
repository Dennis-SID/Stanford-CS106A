package com.cscourse.week5.dsidelnik.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5Part2 extends TextProgram {

        public void run() {
            /* Sit in a loop, reading numbers and adding them. */
            while (true) {
                String n1 = readLine("Enter first number:  ");
                String n2 = readLine("Enter second number: ");
                println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
                println();
            }
        }

        /**
         * Given two string representations of nonnegative integers, adds the
         * numbers represented by those strings and returns the result.
         *
         * @param n1 The first number.
         * @param n2 The second number.
         * @return A String representation of n1 + n2
         */
        private String addNumericStrings(String n1, String n2) {
            int loopLength = Math.max(n1.length(), n2.length());
            int result = 0;
            int remainder = 0;

            List<Character> listNumber1 = stringToCharList(n1);
            List<Character> listNumber2 = stringToCharList(n2);
            Collections.reverse(listNumber1);
            Collections.reverse(listNumber2);

            for (int i = 0; i < loopLength; i++) {
                int firstNumber = (listNumber1.size() < i) ? Character.getNumericValue(listNumber1.get(i)) : 0;
                int secondNumber = (listNumber2.size() < i) ? Character.getNumericValue(listNumber2.get(i)) : 0;
                result = firstNumber + secondNumber;

            }




            return "";
        }

        private List<Character> stringToCharList(String number) {
            return number.chars().mapToObj(x -> (char) x).collect(Collectors.toList());
        }


}
