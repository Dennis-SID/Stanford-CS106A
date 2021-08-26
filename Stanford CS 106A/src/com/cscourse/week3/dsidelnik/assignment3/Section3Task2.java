package com.cscourse.week3.dsidelnik.assignment3;

import java.util.Random;
import java.util.Scanner;

/**
 * Section3 Task2
 * Coin toss game simulation
 * There are two players who has some number of coins in each bag (number of coins defines by user)
 * each player takes a coin from one's bag and toss it, if the coin falls with tails then player save it
 * and put it to his bag, if the coins falls with face then player pass it to another player, then next player will
 * do the same thing
 */
public class Section3Task2 {

    public static void main (String [] args) {
        Section3Task2 s3t2 = new Section3Task2();
        s3t2.playGame();
    }

    /**
     * Starts playing game
     * At the beginning method will ask user for integer input (coins number in each bag)
     * and will print the result of the game
     * in the end of the game method will print the winner (player1 or player2)
     */
    public void playGame() {
        gameLogic();
    }

    /*
     * Helper method
     * Asks user for input
     * used by gameLogic() method
     */
    private int getCoinAmount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter number of coins each player has in a bag");
        return scanner.nextInt();
    }

    /*
     * generates boolean value (true or false) with 50 : 50 probability
     */
    private boolean tossACoin() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /*
     * implements logic of the game
     * method created two integer variables representing bags of two players
     * invokes tossACoin() method and returns the player who will own all coins in the end of the game
     * method prints results of the game to console
     */
    private void gameLogic() {
        int coinAmount = getCoinAmount();
        int player1 = coinAmount;
        int player2 = coinAmount;
        int gameCounter = 0;

        System.out.println("At the start of the game player1 has " + player1 + " coins in the bag");
        System.out.println("and the player2 has " + player2 + " coins in his bag");

        while (player1 > 0 && player2 > 0) {
            if (gameCounter % 2 == 0) {
                if (!tossACoin()) {
                    player1 -= 1;
                    player2 += 1;
                }
            } else {
                if (!tossACoin()) {
                    player2 -= 1;
                    player1 += 1;
                }
            }
            System.out.println("Player1 " + player1 + " coins   " + "Player2 " + player2 + " coins");
            gameCounter++;
        }
        System.out.println("The winner is " + defineWinner(player1) + " with " + Math.max(player1, player2) +
                " coins")   ;
    }

    /*
     * Helper method for gameLogic() method
     * Used to print out the winner
     * takes only one parameter (player1 or player2) defines whether it zero or not
     * returns number of player who has more then zero coins
     */
    private String defineWinner(int player1) {
        if (player1 == 0) {
            return "player2";
        }
        return "player1";
    }
}
