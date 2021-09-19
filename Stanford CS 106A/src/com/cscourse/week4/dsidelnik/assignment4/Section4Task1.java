package com.cscourse.week4.dsidelnik.assignment4;

import acm.graphics.GLine;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

/**
 * Section4 Task1
 * Line rubber. While mouse pressed creates starting point of the line
 * while mouse cursor dragged draws line with end point of cursor
 */
public class Section4Task1 extends WindowProgram {

    // current line
    private GLine line;

    /**
     * Main action method of the class
     */
    public void run() {
        addMouseListeners();
    }


    public void mousePressed(MouseEvent e) {
        line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
        add(line);
    }

    public void mouseDragged(MouseEvent e) {
        line.setEndPoint(e.getX(), e.getY());
    }

}
