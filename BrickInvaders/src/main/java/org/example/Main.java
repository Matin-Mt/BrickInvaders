package org.example;

import org.example.Items.Shooter;
import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet applet;

    public static int windowWidth = 400;
    public static int windowLength = 700;

    private Wave wave;
    public static Shooter shooter;

    public static String menu = "main_menu";
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
            }
        }

        if (menu.equals("game_over_menu")) {
            Menu.game_over_menu();
        }

        if (menu.equals("record_menu")) {
            background(250);
            Menu.record_menu();
        }

        if (menu.equals("help_menu")) {
            background(255);
            Menu.help_menu();
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

            } else if (mouseY >= 225 && mouseY <= 255) {
                if (mouseX >= 165 && mouseX <= 235)
                    menu = "help_menu";

            } else if (mouseY >= 275 && mouseY <= 300) {
                if (mouseX >= 170 && mouseX <= 230)
                    System.exit(0);
            }
        }

        if (menu.equals("game_over_menu")) {
            if (mouseX >= 160 && mouseX <= 240)
                if (mouseY >= 190 && mouseY <= 220)
                    menu = "main_menu";
        }

        if (menu.equals("record_menu")) {
            if (mouseY >= 380 && mouseY <= 400)
                if (mouseX >= 170 && mouseX <= 225)
                    menu = "main_menu";
        }

        if (menu.equals("help_menu")) {
            if (mouseY >= 430 && mouseY <= 450)
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
                        if (shooter.getLevelUpPointer() >= 2) {
                            if (keyCode == 'q' || keyCode == 'Q') {
                                shooter.addHP();
                            }
                        }
                        if (keyCode == 'w' || keyCode == 'W') {
                            shooter.addShotPower();
                        }
                        if (keyCode == 'e' || keyCode == 'E') {
                            shooter.addShotSpeed();
                        }
                    }
                }
            }
        }
    }
}