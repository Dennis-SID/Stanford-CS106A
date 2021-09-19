package com.cscourse.week5.dsidelnik.assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Section5Task1 {

    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputList = new ArrayList<>();
        int userInput;
        while(true) {
            while ((userInput = Integer.parseInt(scanner.next())) != -1) {
                inputList.add(userInput);
            }

            Section5Task1 s5t1 = new Section5Task1();
            System.out.println("Unsorted list: " + inputList);
            System.out.println("Sorted list with selection sort: " + s5t1.selectionSorting(inputList));
        }


    }

    public List<Integer> selectionSorting (List<Integer> inputList) {
        List<Integer> unsortedList = new ArrayList<>(inputList);
        int current;
        int smallest;
        int index;

        for (int i = 0; i < unsortedList.size() - 1; i++) {
            current = unsortedList.get(i);
            smallest = current;
            index = i;
            for (int j = i + 1; j < unsortedList.size(); j++) {
                if (current > unsortedList.get(j)) {
                    smallest = unsortedList.get(j);
                    index = j;
                }
            }
            unsortedList.set(i, smallest);
            unsortedList.set(index, current);
        }
        return unsortedList;
    }
}
