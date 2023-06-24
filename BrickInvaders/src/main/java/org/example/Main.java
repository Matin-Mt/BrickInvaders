package org.example;

import org.example.Items.Boss;
import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet applet;

    public static int windowWidth = 400;
    public static int windowLength = 700;

    public Boss boss;

    public static void main(String[] args) {
        PApplet.main("org.example.Main");
    }

    @Override
    public void setup() {
        applet = this;
        boss = new Boss(0, 0);
    }

    @Override
    public void settings() {
        size(windowWidth, windowLength);
        smooth(55);
    }

    @Override
    public void draw() {
        background(75, 191, 199);
        boss.show();

    }
}