package com.cscourse.week3.dsidelnik.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Assignment3 Part1
 * <p>Medics are recommend 30 minutes aerobic exercises five days a week
 * for maintaining you cardiovascular health in a good condition. In addition at least
 * 40 minutes three times a week for keeping healthy heart pressure</p>
 * This program asks user amount of minutes the latter spent on exercises over last seven days (week) and makes
 * the next reports:
 * <p> - whether was it enough for healthy cardiovascular health, and if no prints: how many healthy lifestyle days
 * <strong>(with exercises over 30 minutes)</strong> lacking for recommended routine </p>
 * <p> - whether was it enough exercises for decreasing heart pressure and cholesterol level, and if no prints
 * : how many healthy days <strong>(having over 40 minutes exercises)</strong> days lack for healthy lifestyle. </p>
 */
public class Assignment3Part1 extends TextProgram {

    /* Sets number of days */
    private final int DAYS = 7;

    /* Gets user input */
    private final Scanner SCANNER = new Scanner(System.in);

    /* sets amount of time for cardiovascular exercises */
    private final int CV_EXERCISE_TIME = 30;

    /* sets amount of time for heart pressure exercises */
    private final int HP_EXERCISE_TIME = 40;

    /* sets numbers of training days for healthy cardiovascular system */
    private final int CV_DAYS_NUMBER = 5;

    /* sets number of training days for healthy heart pressure */
    private final int HP_DAYS_NUMBER = 3;

    /**
     * Main action method that starts a program
     */
    public void run() {
        List<Integer> dayTime = getExerciseTime();
        cvHealth(dayTime);
        hpHealth(dayTime);
    }

    /**
     * Asks user for input amount of time spent on exercises each day
     * number of days can be changed through making change in final variable "DAYS"
     *
     * @return list of time was spent on exercises each day
     */
    private List<Integer> getExerciseTime() {
        List<Integer> dayTime = new ArrayList<>();
        for (int i = 0; i < DAYS; i++) {
            System.out.println("How many minutes did you do on day " + (i + 1) + " ?");
            dayTime.add(SCANNER.nextInt());
        }
        return dayTime;
    }

    /**
     * Defines whether was enough exercises for cardiovascular health
     *
     * @param dayTime list of training time for each day
     */
    private void cvHealth(List<Integer> dayTime) {
        List<Integer> filteredDays = dayTime.stream().filter(x -> x >= CV_EXERCISE_TIME).collect(Collectors.toList());

        if (filteredDays.size() >= CV_DAYS_NUMBER)
            System.out.println("Great job! You've done enough exercise for cardiovascular health.");
        else
            System.out.println("You needed to train hard for at least " + (CV_DAYS_NUMBER - filteredDays.size())
                    + " more day(s) a week!");
    }

    /**
     * Defines whether was enough exercises for healthy heart pressure
     *
     * @param dayTime list of training time for each day
     */
    private void hpHealth(List<Integer> dayTime) {
        List<Integer> filteredDays = dayTime.stream().filter(x -> x >= HP_EXERCISE_TIME).collect(Collectors.toList());

        if (filteredDays.size() >= HP_EXERCISE_TIME)
            System.out.println("Great job! you've done enough exercise to keep a low bloo30d pressure.");
        else
            System.out.println("You needed to train hard for at least " + (HP_DAYS_NUMBER - filteredDays.size())
                    + " more day(s) a week!");
    }
}
