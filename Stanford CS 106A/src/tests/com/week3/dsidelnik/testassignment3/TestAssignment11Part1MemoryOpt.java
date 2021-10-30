package tests.com.week3.dsidelnik.testassignment3;

import com.cscourse.week11.dsidelnik.assignment11.memoryoptimized.Assignment11Part1;

import java.io.IOException;

public class TestAssignment11Part1MemoryOpt {

    public static void main (String [] args) {
        String filePath = "assets/imgs/";
        try {
            assertEquals(1, new String[] {"assets/imgs/tst_01.jpg"}, "1");
            assertEquals(4, new String[] {"assets/imgs/tst_02.png"}, "2");
            assertEquals(2, new String[] {"assets/imgs/tst_03.jpg"}, "3");
            assertEquals(4, new String[] {"assets/imgs/tst_04.png"}, "4");
            assertEquals(3, new String[] {"assets/imgs/tst_05.jpg"}, "5");
            assertEquals(1, new String[] {"assets/imgs/tst_06.jpg"}, "6");
            assertEquals(13, new String[] {"assets/imgs/tst_07.jpg"}, "7");
            assertEquals(1, new String[] {"assets/imgs/tst_08.jpg"}, "8");
            assertEquals(35, new String[] {"assets/imgs/tst_10.jpg"}, "10");
            assertEquals(7, new String[] {"assets/imgs/tst_12.jpg"}, "12");
            assertEquals(13, new String[] {"assets/imgs/tst_16.bmp"}, "16");
            assertEquals(1, new String[] {"assets/imgs/tst_18.jpg"}, "18");
            assertEquals(3, new String[] {"assets/imgs/tst_20.jpg"}, "20");
            assertEquals(6, new String[] {"assets/imgs/tst_22.jpg"}, "22    ");
            assertEquals(20, new String[] {"assets/imgs/tst_23.jpg"}, "23    ");
            assertEquals(1, new String[] {"assets/imgs/tst_25.jpg"}, "25");
            assertEquals(13, new String[] {"assets/imgs/tst_29.jpg"}, "29");
            assertEquals(6, new String[] {"assets/imgs/tst_42.png"}, "42");
            assertEquals(3, new String[] {"assets/imgs/tst_46.jpeg"}, "46");
            assertEquals(1, new String[] {"assets/imgs/tst_47.png"}, "47");
            assertEquals(1, new String[] {"assets/imgs/tst_48.png"}, "48");
            assertEquals(4, new String[] {"assets/imgs/tst_49.png"}, "49");
            assertEquals(5, new String[] {"assets/imgs/tst_56.png"}, "56");
            assertEquals(10, new String[] {"assets/imgs/tst_57.jpg"}, "57");
            assertEquals(4, new String[] {"assets/imgs/tst_64.png"}, "64");
            assertEquals(8, new String[] {"assets/imgs/tst_58.jpg"}, "58");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    public static void assertEquals(int expected, String[] filePath, String testNumber) throws IOException {
        Assignment11Part1 sil = new Assignment11Part1();
        // to test class create test method in class
        /*int silNumber = sil.testSil(filePath);
        if (silNumber == expected) {
            System.out.println(testNumber + " ( V ) Success!! actual number: " + silNumber + "Expected number: " + expected);
        } else {
            System.out.println(testNumber + "( X ) FAIL!!! actual number: " + silNumber + "Expected number: " + expected);
        }*/
    }
}
