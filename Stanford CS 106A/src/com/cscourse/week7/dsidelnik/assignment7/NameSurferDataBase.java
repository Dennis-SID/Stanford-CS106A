package com.cscourse.week7.dsidelnik.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NameSurferDataBase implements NameSurferConstants {

    // stores information read from a file
    private final Map<String, NameSurferEntry> DATA_BASE;

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase() {
        DATA_BASE = new HashMap<>();
        try {
            String line;
            BufferedReader dataReader = new BufferedReader(new FileReader(NAMES_DATA_FILE));
            while ((line = dataReader.readLine()) != null) {
                NameSurferEntry entry = new NameSurferEntry(line);
                DATA_BASE.put(entry.getName(), entry);
            }
        } catch (IOException e) {
            System.out.println("That file does not exist! Please check the name of the file and try again");
            e.printStackTrace();
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return DATA_BASE.getOrDefault(name, null);
    }

    /**
     * Getter method to encapsulate dataBase variable which stores name ad years data from a file
     * ------------------------------------------------------------------------------------------
     *                              Used in main NameSurfer class in
     * ------------------------------------------------------------------------------------------
     * @return HashMap instance variable "dataBase"
     */
    public Map<String, NameSurferEntry> getDATA_BASE() {
        return DATA_BASE;
    }
}

