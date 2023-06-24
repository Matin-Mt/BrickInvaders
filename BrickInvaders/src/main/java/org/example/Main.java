package org.example;

import org.example.Items.Block;
import processing.core.PApplet;

import javax.swing.text.html.HTMLDocument;

public class Main extends PApplet {
    public static PApplet applet;

    public static int windowWidth = 400;
    public static int windowLength = 700;

    String menu = "main_menu";

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
        if (menu.equals("main_menu")) {
            background(250);
            Menu.main_menu();
        }

        if (menu.equals("record_menu")) {
            background(250);
            Menu.record_menu();
        }
    }

    @Override
    public void mousePressed() {
        if (menu.equals("main_menu")) {
            if (mouseY >= 125 && mouseY <= 150) {
                if (mouseX >= 90 && mouseX <= 310)
                    menu = "game_menu";

            } else if (mouseY >= 175 && mouseY <= 200) {
                if (mouseX >= 85 && mouseX <= 315)
                    menu = "record_menu";

            } else if (mouseY >= 225 && mouseY <= 250) {
                if (mouseX >= 175 && mouseX <= 225)
                    System.exit(0);
            }
        }

        if (menu.equals("record_menu")) {

        }
    }
}