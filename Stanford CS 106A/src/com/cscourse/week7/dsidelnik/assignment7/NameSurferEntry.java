package com.cscourse.week7.dsidelnik.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;
import java.util.stream.Collectors;

public class NameSurferEntry implements NameSurferConstants {

    // stores name and it ranks on decades
    String name;
    List<Integer> yearRanks;


    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        List<String> temporaryList = parseLine(line);
        name = getName(temporaryList);
        yearRanks = getYears(temporaryList);
    }

    /* Constructor helper method
     * used to create NameSurferEntry object
     * and assigns variables name as yearRank with data took from a database
     */
    private List<String> parseLine(String data) {return Arrays.asList(data.split(" "));}

    private String getName(List<String> dataList) {return dataList.get(0).toLowerCase();}

    /* removes data with a name and returns a list of years */
    private List<Integer> getYears(List<String> dataList) {
        List<String> processList = new ArrayList<>(dataList);
        processList.remove(0);
        return processList.stream().map(Integer::parseInt).collect(Collectors.toList());
    }


    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return yearRanks.get(decade);
    }


    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return name + " " + yearRanks;
    }
}

