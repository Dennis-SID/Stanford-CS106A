package com.cscourse.week3.dsidelnik.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Assignment3 Part5
 * Program that imitates hypothetical casino game
 * Rules of the game are next:
 * - two player are playing (lucky and sweated)
 * - sweated put a 1$ on the gaming table and lucky tossing a coin
 * if coin fall down with a head faced up the sweated put the same sum on the table
 * which already there (double the sum of money on the table) if the coin face a tail the game
 * starts again
 * - game last until lucky will earn 20$ or more in total
 */
public class Assignment3Part5 extends TextProgram {

    /**
     * Main action method that start a game
     * have endless loop, will end it's implementations when user enter y (in an upper or lower case)
     * catch Exception which may occur using Scanner class
     */
    public void run() {
        try (Scanner scanner = new Scanner(in)) {
            while (true) {
                play();
                System.out.println("THE GAME IS OVER");
                System.out.println("WOULD YOU LIKE TO PLAY AGAIN (YES - enter \"Y\" NO - enter any symbol)");
                String userDecision = scanner.next();
                if (!userDecision.equalsIgnoreCase("y")) return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Starts the game
     * Uses tossACoin method, do not returns any value
     * prints results of the game in a command line
     */
    private void play() {
        int totalEarnings = 0; // stores total cash
        int roundCash = 1; // stores round cash each round starts with value 1
        int gameCounter = 0; // counts number of game and prints it in the end of game
        while(totalEarnings < 20) {
            while(tossACoin()) {
                roundCash *= 2; // if coin show it head doubles money on a table
            }
            totalEarnings += roundCash;
            gameCounter++;
            System.out.println("This game you earned $" + roundCash);
            System.out.println("Your total is $" + totalEarnings);
            roundCash = 1;
        }
        System.out.println("It took " + gameCounter + " games to earn $20");
    }

    /**
     * Imitates a tossing a coin which has 50/50 percent probability
     * to fall with a head or tail faced up
     * @return boolean true or false (head or tail)
     */
    private boolean tossACoin() {
        Random random = new Random();
        return random.nextInt(2) == 1;
    }
}
