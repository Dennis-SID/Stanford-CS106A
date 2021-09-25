package tests.com.week3.dsidelnik.testassignment3;

import com.cscourse.week5.dsidelnik.assignment5.*;

import java.util.Arrays;
import java.util.List;

public class TestSection5Task1 {

    public static void main(String[] args) {
        Section5Task1 s5t1 = new Section5Task1();

        List<Integer> testList = Arrays.asList(3, 2, 1, 5, 4, 7, 9, 1, 5, 8, 1, 4, 6, 1, 1, 1, 8, 9);
        List<Integer> testList2 = Arrays.asList(3, 3, 3, 1, 1, 1, 1, 1);
        System.out.println(s5t1.selectionSorting(testList));
        System.out.println(s5t1.selectionSorting(testList2));
    }
}
