package org.example;

import org.example.Items.Shooter;
import processing.core.PApplet;
import processing.event.MouseEvent;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class Main extends PApplet {
    public static PApplet applet;

    public static int windowWidth = 400;
    public static int windowLength = 700;

    private Wave wave;
    public static Shooter shooter;

    private String menu = "main_menu";
    public static boolean gameStarted = false;

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
            gameStarted = false;
            background(250);
            Menu.main_menu();
        }

        if (menu.equals("game_menu")) {
            if (!gameStarted) {
                gameStarted = true;
                shooter = new Shooter(mouseX, windowLength - 50);
                wave = new Wave(shooter);
            }
            Menu.game_menu(wave);
            if (!shooter.exist()) {
                gameStarted = false;
                // lose menu
            }
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
            if (mouseY >= 380 && mouseY <= 400)
                if (mouseX >= 170 && mouseX <= 225)
                    menu = "main_menu";
        }
    }

    @Override
    public void keyPressed() {
        if (keyPressed) {
            if (menu.equals("game_menu")) {
                if (gameStarted) {
                    if (shooter.getLevelUpPointer() > 0){
                        if (keyCode == 'q' || keyCode == 'Q') {
                            shooter.addHP();
                            System.out.println(1);
                            System.out.println(keyCode);
                            System.out.println(key);
                        }
                        if (keyCode == 'w' || keyCode == 'W') {
                            shooter.addShotPower();
                            System.out.println(2);
                            System.out.println(keyCode);
                            System.out.println(key);
                        }
                        if (keyCode == 'e' || keyCode == 'E') {
                            shooter.addShotSpeed();
                            System.out.println(3);
                            System.out.println(keyCode);
                            System.out.println(key);
                        }
                    }
                }
            }
        }
    }
}