package com.cscourse.week11.dsidelnik.assignment11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Node {
    private int positionX;
    private int positionY;
    Color color;
    private List<Node> connectedNodes;

    int red;
    int green;
    int blue;
    int alpha;

    boolean visited;

    List<Node> references;

    public Node () {
        connectedNodes = new ArrayList<>();
    }

    public Node(int positionX, int positionY, Color color) {
        this();
        references = new ArrayList<>();
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getAlpha();
    }

    public int getPositionX() {return positionX; }
    public int getPositionY() {return positionY; }

    public int getRedColor() { return red; }
    public int getGreenColor() {return green; }
    public int getBlueColor() {return blue; }
    public int getAlphaChannel() { return alpha; }
    public Color getColor() {return color;}

    public void setPositionX(int xPosition) { positionX = xPosition; }
    public void setPositionY(int yPosition) { positionY = yPosition; }
    public void setRedColor(int red) { this.red = red; }
    public void setGreenColor(int green)  { this.green = green; }
    public void setBlueColor(int blue) { this.blue = blue; }
    public void setAlphaChannel(int aloha) { this.alpha = alpha; }


    public List<Node> getAllNodes() {
        return connectedNodes;
    }

    public void addNode(Node node) {
        connectedNodes.add(node);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setAsVisited() {
        visited = true;
    }
}
