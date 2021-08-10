package week1.dsidelnik.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Assignment1 Part1
 * Training task to make Karel bring the newspaper
 * @author yourname
 * @version 1.0
 */
public class Assignment1Part1 extends KarelTheRobot {

    /**
     * KareTheRobot main action method
     */
    public void run() {
        try {
            moveToNewspaper();
            getNewspaper();
            moveBack();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * make Karel move to the newspaper
     */
    private void moveToNewspaper() throws Exception {
        turnRight();
        move();
        turnLeft();
        moveForward();
    }

    /**
     * makes Karel move back to the starting position
     */
    private void moveBack() throws Exception {
        turnLeft();
        turnLeft();
        moveForward();
        turnRight();
        move();
        turnRight();
    }

    /**
     * Check is the newspaper present and pick up it is so
     */
    private void getNewspaper() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
        }
    }

    /**
     * make Karel move 4 steps forward
     */
    private void moveForward() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    /**
     * makes Karel turn right
     */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

}
