package com.cscourse.week11.dsidelnik.assignment11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Node class, used to create a graph of nodes
 * each node represent a pixel of an image
 * stores colors, transparency properties
 * also stores information about neighbour nodes and whether the
 * node was visited or not through traverse
 */
public class Node {
    private int positionX;
    private int positionY;
    Color color;

    /*
     * stores all connected nodes,
     * in case of image matrix all neighbour nodes (is pixel in the center
     * of image there are  four (one left one right one above up and one below)
     */
    private final List<Node> connectedNodes;

    int red;
    int green;
    int blue;
    int alpha;

    boolean visited;


    public Node() {
        connectedNodes = new ArrayList<>();
    }

    public Node(int positionX, int positionY, Color color) {
        this();
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getAlpha();
    }

    /* node class getters  */
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getRedColor() {
        return red;
    }

    public int getGreenColor() {
        return green;
    }

    public int getBlueColor() {
        return blue;
    }

    public int getAlphaChannel() {
        return alpha;
    }

    public Color getColor() {
        return color;
    }

    /* node class setters */
    public void setPositionX(int xPosition) {
        positionX = xPosition;
    }

    public void setPositionY(int yPosition) {
        positionY = yPosition;
    }

    public void setRedColor(int red) {
        this.red = red;
    }

    public void setGreenColor(int green) {
        this.green = green;
    }

    public void setBlueColor(int blue) {
        this.blue = blue;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAlphaChannel(int alpha) {
        this.alpha = alpha;
    }

    /**
     * returns all neighbour nodes current node connected to
     *
     * @return node list
     */
    public List<Node> getConnectedNodes() {
        return connectedNodes;
    }

    /**
     * Adds connection to a neighbour nnode
     *
     * @param node gets connected node and stores it into instance node list
     */
    public void addNode(Node node) {
        connectedNodes.add(node);
    }

    /**
     * Returns true or false whether node was visited or not
     *
     * @return if was visited will return true, alse false
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * sets private visited variable to true
     * indicating that the node was visited during traverse
     */
    public void setAsVisited() {
        visited = true;
    }
}
