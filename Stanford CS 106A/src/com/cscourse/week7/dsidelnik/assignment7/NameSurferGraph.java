package com.cscourse.week7.dsidelnik.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.List;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    // stores entries typed by user
    List<NameSurferEntry> nameSurferEntryList = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        update();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        nameSurferEntryList.clear();
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        nameSurferEntryList.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGraphGrid();
        drawGraphs();
    }

    /**
     * draws horizontal and vertical lines also draws years of decades
     */
    private void drawGraphGrid() {
        int bottomYearMargin = 5;
        // draws vertical lines
        int upperMargin = GRAPH_MARGIN_SIZE;
        int bottomMargin = getHeight() - upperMargin;
        add(new GLine(0, upperMargin, getWidth(), upperMargin));
        add(new GLine(0, bottomMargin, getWidth(), bottomMargin));

        // draws horizontal lines and years on the bottom of the canvas
        int pointX = 0;
        int decadeNumber = 0;
        String label = START_DECADE + "";
        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(pointX, 0, pointX, getHeight()));
            add(new GLabel(label), pointX, getHeight() - bottomYearMargin);
            pointX += getWidth() / NDECADES;
            decadeNumber += 10;
            label = (START_DECADE + decadeNumber) + "";

        }

    }

    /**
     * draws all graphs, uses helper method drawSingleGraph
     */
    private void drawGraphs() {
        Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
        int counter = 0;

        for (NameSurferEntry entry : nameSurferEntryList) {
            drawSingleGraph(entry, colors[counter % colors.length]);
            counter++;
        }
    }

    /* Helper method used by method drawGraphs to draw a single graph */
    private void drawSingleGraph(NameSurferEntry entry, Color color) {
        double startXPoint = 0.0;
        double startYPoint = defineY(entry.getRank(0));

        for (int i = 0; i < NDECADES; i++) {
            double currX = i * ((double)getWidth() / NDECADES);
            double currY = defineY(entry.getRank(i));

            String labelRank = (entry.getRank(i) == 0) ? "*" : "" + entry.getRank(i);

            drawLine(startXPoint, startYPoint, currX, currY, color);
            drawLabel(currX, currY, capitalizer(entry.getName()) + " " + labelRank, color);

            startXPoint = currX;
            startYPoint = currY;
        }
    }

    /* helper method used by drawSingleGraph method */
    private int defineY(int rating) {
        return (rating == 0) ? getHeight() - GRAPH_MARGIN_SIZE
                : GRAPH_MARGIN_SIZE + (rating * (getHeight() - GRAPH_MARGIN_SIZE * 2)) / MAX_RANK;
    }

    /* helper method used to draw a single line */
    private void drawLine(double startX, double startY, double endX, double endY, Color color) {
        GLine resultLine = new GLine(startX, startY, endX, endY);
        resultLine.setColor(color);
        add(resultLine);
    }

    /* helper method used to draw a single line */
    private void drawLabel(double pointX, double pointY, String text, Color color) {
        GLabel label = new GLabel(text);
        label.setLocation(pointX, pointY);
        label.setColor(color);
        add(label);
    }

    /* helper method used to make a name beginning with a capital letter */
    private String capitalizer(String name) {
        char[] array = name.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
