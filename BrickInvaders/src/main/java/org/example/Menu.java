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
    }

    public static void game_menu(Wave wave) {
        a.background(0, 255, 255);
        if (wave.exist()) {
            wave.healthChecking();
            wave.move();
            wave.show();
        } else {
            wave.levelWave();
        }

        a.fill(52, 199, 84);
        a.noStroke();
        a.rect(0, 0, Main.windowWidth, 50);
        a.fill(255, 0, 0);
        a.textAlign(a.LEFT);
        a.textSize(14);
        a.text("HP:(1) " + wave.getShooter().getHP(), 5, 20);
        a.text("Shot Power:(2) " + wave.getShooter().getShotPower(), 95, 20);
        a.text("Shot Rate:(3) " + (-1 * wave.getShooter().getShotSpeed()), 265, 20);

        a.text("Level: " + wave.getShooter().getLevel(), 5, 40);
        a.text("EXP: " + wave.getShooter().getCurrent_EXP() + "/" + wave.getShooter().getMax_EXP(), 95, 40);
        a.text("Levelup Points: " + wave.getShooter().getLevelUpPointer(), 265, 40);

        a.fill(52, 199, 84);
        a.rect(0, Main.windowLength - 50, Main.windowWidth, 50);
        a.textAlign(a.CENTER);
        a.fill(255, 0, 0);
        a.textSize(24);
        a.text("WAVE: " + wave.getLevel(), Main.windowWidth / 2f, Main.windowLength - 20);
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
            }
        }

    }
}
