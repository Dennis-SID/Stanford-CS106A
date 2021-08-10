package week1.dsidelnik.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Assignment1 Part3
 * Middle point finder
 */
public class Assignment1Part3 extends KarelTheRobot {

    /**
     * Class starter method
     */
    public void run() {
        try {
            if (frontIsBlocked()) {
                turnLeft();
                if (frontIsBlocked()) {
                    putBeeper();
                    return;
                }
            }
            defineCenter();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that defines center
     */
    private void defineCenter() throws Exception {
        makeFirstPoints();
        while (beepersPresent()) {
            moveForward();
            turnBack();
            changeBeeperPlace();
        }
        putBeeper();
    }

    /**
     * Makes first points of beepers
     */
    private void makeFirstPoints() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            move();
        }
        if (!beepersPresent()) putBeeper();
        turnBack();
    }

    /**
     * Moves Karel forward
     */
    private void moveForward() throws Exception {
        move();
        while (!beepersPresent()) {
            move();
        }

    }

    /**
     * Makes Karel turn back
     */
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * Makes Karel pick up a beeper and move it one step forward
     */
    private void changeBeeperPlace() throws Exception {
        pickBeeper();
        move();
        if (!beepersPresent()) putBeeper();
        else pickBeeper();
    }
}
