package com.cscourse.week7.dsidelnik.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */


import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private JButton clearButton;
    private JButton graphButton;
    private JTextField textField;

    private NameSurferGraph graph;
    private NameSurferDataBase dataBase;


    /**
     * Init method assigns JButton variables: clearButton, graphButton
     * JTextField variable textField, and NameSurferGraph and NameSurferDataBaase
     * variables
     */
    public void init() {
        clearButton = new JButton("Clear");
        graphButton = new JButton("Graph");
        textField = new JTextField(TEXT_FIELD_SIZE);
        JLabel label = new JLabel("Name: ");
        graph = new NameSurferGraph();
        add(label, NORTH);
        add(textField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        add(graph);

        addActionListeners();
        dataBase = new NameSurferDataBase();
        /*textField.setActionCommand("EnterPressed");*/
        textField.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    graphActionPerformed();
                }
            }

            // methods that needs to be overriden
            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}

        });

    }


    /**
     * Action listener method
     * listen for actions made up by program user and
     * invoke defined actions
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == graphButton) {
            graphActionPerformed();
        }
        if (e.getSource() == clearButton) {
            graph.clear();
        }

    }

    /**
     * Method that invokes all the methods to add and draw grid and graph
     * used by action listeners (actionPerformed and keyPressed methods)
     */
    private void graphActionPerformed() {
        String text = textField.getText().toLowerCase();
        if (!textField.getText().equals("") && dataBase.getDATA_BASE().containsKey(text)) {
            NameSurferEntry entry = dataBase.findEntry(text);
            graph.addEntry(entry);
            graph.update();
            textField.setText("");
        }
    }
}
