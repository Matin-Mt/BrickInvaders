package org.example;

import processing.core.PApplet;

import java.util.ArrayList;

public class Menu {
    public static PApplet a = Main.applet;

    private static Database database = new Database();

    public static void main_menu() {
        a.fill(212, 30, 33);
        a.textSize(32);
        a.textAlign(a.CENTER);
        a.text("Main Menu", Main.windowWidth / 2f, 50);
        a.text("Start New Game", Main.windowWidth / 2f, 150);
        a.text("View Top Players", Main.windowWidth / 2f, 200);
        a.text("Exit", Main.windowWidth / 2f, 250);
        a.text("X: " + a.mouseX, Main.windowWidth / 2f, 300);
        a.text("Y: " + a.mouseY, Main.windowWidth / 2f, 350);
    }

    public static void record_menu() {
        ArrayList<String> records = database.getTopRecords();
        float minY = 150;

        a.fill(212, 30, 33);
        a.textSize(32);
        a.textAlign(a.CENTER);
        a.text("record Menu", Main.windowWidth / 2f, 50);

        a.textSize(24);
        for (int i = 0; i < 5; i++) {
            if (records.get(i) != null){
                a.text(records.get(i), Main.windowWidth / 2f, minY);
            } else if (i == 0) {
                a.text("Players Are Yet To Be Recorded!", Main.windowWidth / 2f, minY);
            }
            minY += 50;

            if (i == 4) {
                a.text("Back", Main.windowWidth / 2f, minY);
                a.text("X: " + a.mouseX, Main.windowWidth / 2f, minY + 50);
                a.text("Y: " + a.mouseY, Main.windowWidth / 2f, minY + 100);
            }
        }

    }
}
