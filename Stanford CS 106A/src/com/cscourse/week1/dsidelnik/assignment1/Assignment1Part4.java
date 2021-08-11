package week1.dsidelnik.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Assignment1 Part4
 * Chess board task
 */
public class Assignment1Part4 extends KarelTheRobot {

    /**
     * Starting point of the program
     */
    public void run() {
        try {
            buildChessBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds checker board
     */
    private void buildChessBoard() throws Exception {
        buildOddRow();
        moveBack();
        while (leftIsClear()) {
            toNextRow();
            buildEvenRow();
            moveBack();
            if (leftIsBlocked()) return;
            toNextRow();
            buildOddRow();
            moveBack();
        }
    }

    /**
     * Builds first and other odd rows
     */
    private void buildOddRow() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            if (!beepersPresent()) putBeeper();
            move();
            if (frontIsBlocked()) break;
            move();
            if (!beepersPresent()) putBeeper();
        }
        turnBack();
    }

    /**
     * Builds second and other even rows
     */
    private void buildEvenRow() throws Exception {
        while (frontIsClear()) {
            move();
            if (!beepersPresent()) putBeeper();
            if (frontIsBlocked()) break;
            move();
        }
        turnBack();
    }

    /**
     * Makes Karel move back to the beginning of the row
     */
    private void moveBack() throws Exception {
        while (frontIsClear()) {
            move();
        }
        turnBack();
    }

    /**
     * Makes Karel turn back
     */
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * Helper method that makes Karel turn right
     */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    /**
     * If the next row exist make Karel move to the next row
     */
    private void toNextRow() throws Exception {
        if (leftIsClear()) {
            turnLeft();
            move();
            turnRight();
        }
    }

}
