package org.example;

import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet applet;

    public static int windowWidth = 400;
    public static int windowLength = 700;

    public static void main(String[] args) {
        PApplet.main("org.example.Main");
    }

    @Override
    public void setup() {
        applet = this;
    }

    @Override
    public void settings() {
        size(windowWidth, windowLength);
        smooth(55);
    }

    @Override
    public void draw() {
        background(75, 191, 199);

    }
}