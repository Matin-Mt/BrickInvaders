package org.example;

import org.example.Items.Shooter;
import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet applet;

    private static int sunSpeed = 50;

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
            wallpaper(sunSpeed);
            sunSpeed += 1;
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

    private void wallpaper(int xCoordinate) {
        int yCoordinate = 90;

        background(75, 191, 199);

        // SUN
        fill(232, 207, 21);
        if (xCoordinate >= 30 && xCoordinate < windowWidth - 30) {
            circle(xCoordinate, yCoordinate, 30);
        } else if (xCoordinate >= windowWidth - 30 && xCoordinate < windowWidth + 30) {
            circle(xCoordinate, yCoordinate, 30);
            circle(xCoordinate - windowWidth, yCoordinate, 30);
        } else if (xCoordinate >= windowWidth + 30) {
            wallpaper(xCoordinate - windowWidth);
        }

        // CLOUDS
        fill(93, 65, 201);

        // CLOUD 1
        if (xCoordinate - 70 >= 19 && xCoordinate - 70 <= windowWidth - 19) {
            createCloud(xCoordinate - 70, yCoordinate - 10);
        } else if (xCoordinate - 70 >= windowWidth - 19 || xCoordinate - 70 <= windowWidth + 19) {
            createCloud(xCoordinate - 70, yCoordinate - 10);
            createCloud(xCoordinate - 70 - windowWidth, yCoordinate - 10);
        }

        // CLOUD 2
        if (xCoordinate >= 19 && xCoordinate + 90 <= windowWidth - 19) {
            createCloud(xCoordinate + 90, yCoordinate + 50);
        } else if (xCoordinate + 90 >= windowWidth - 19 || xCoordinate + 90 <= windowWidth + 19) {
            createCloud(xCoordinate + 90, yCoordinate + 50);
            createCloud(xCoordinate + 90 - windowWidth, yCoordinate + 50);
        }

        // CLOUD 3
        if (xCoordinate >= 19 && xCoordinate + 170 <= windowWidth - 19) {
            createCloud(xCoordinate + 170, yCoordinate + 15);
        } else if (xCoordinate + 170 >= windowWidth - 19 || xCoordinate + 170 <= windowWidth + 19) {
            createCloud(xCoordinate + 170, yCoordinate + 15);
            createCloud(xCoordinate + 170 - windowWidth, yCoordinate + 15);
        }
    }

    private void createCloud(int xCoordinate, int yCoordinate) {
        circle(xCoordinate - 19, yCoordinate,20); // most left
        circle(xCoordinate - 10, yCoordinate - 7,20); // upper left
        circle(xCoordinate - 7, yCoordinate + 5,20); // lower left
        circle(xCoordinate + 19, yCoordinate,20); // most right
        circle(xCoordinate + 7, yCoordinate - 7,20); // upper right
        circle(xCoordinate + 9, yCoordinate + 5,20); // lower right
    }
}