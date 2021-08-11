package week1.dsidelnik.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Assignment1 Part2
 * Karel the robot building columns task
 */
public class Assignment1Part2 extends KarelTheRobot {

    /**
     * main starter method
     */
    public void run() {
        try {
            buildColumn();
            while (!frontIsBlocked()) {
                nextColumn();
                buildColumn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * makes Karel build a column
     * For this method to work correctly Karel must be facing east
     */
    private void buildColumn() throws Exception {
        turnLeft();
        deployBeeper();
        while (!frontIsBlocked()) {
            move();
            deployBeeper();
        }
        turnLeft();
        turnLeft();
        while (!frontIsBlocked()) {
            move();
        }
        turnLeft();
    }

    /**
     * Moves Karel to the next column
     * For thid method to work correctly Karel must be facing east
     */
    private void nextColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            if (frontIsClear()) move();
        }
    }

    /**
     * Checks whether beeper is present if no puts one
     * Method assumes that Karel in process of building a column
     */
    private void deployBeeper() throws Exception {
        if (!beepersPresent()) {
            putBeeper();
        }
    }
}
