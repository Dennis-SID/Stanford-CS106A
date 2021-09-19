package com.cscourse.week4.dsidelnik.assignment4;

import java.util.Scanner;

public class Section4Task2 {

    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        Section4Task2 s4t2 = new Section4Task2();

        while(true) {
            System.out.println("Please enter a number: ");
            System.out.println(" decorated number is : " + s4t2.numberDecorator(scanner.next()));
        }
    }

    public String numberDecorator(String number) {

        int counter  = 0;
        StringBuilder resultString = new StringBuilder();

        for (int i = number.length() - 1; i >= 0; i--) {
            if (counter == 3) {
                resultString.append(",");
                counter = 0;
            }

            resultString.append(number.charAt(i));
            counter++;
        }
        resultString.reverse();
        return resultString.toString();
    }
}
