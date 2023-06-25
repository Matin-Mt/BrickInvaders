package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;
import processing.core.PApplet;

public class Block extends Item implements Movable {
    public static PApplet a = Main.applet;

    private static double Health = 2;
    private static int EXP = 1;
    public static double speedY = 1;

    public static int blockWidth = 15;
    public static int blockLength = 50;

    public Block(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public void healthChecking() {
        if (exist()) {
            if (Health <= 0) {
                setExist(false);
            }

            if (getYCoordinate() >= Main.windowLength) {
                setExist(false);
            }
        }
    }

    @Override
    public void show() {
        if (exist() && yCoordinate < Main.windowLength - 50){
            a.fill(174, 255, 0);
            a.rect((float) getXCoordinate(), (float) getYCoordinate(), blockLength, blockWidth);
            a.fill(255, 0, 0);
            a.textAlign(a.CENTER);
            a.textSize(14);
            a.text(Double.toString(Health), (float) ((blockLength / 2) + xCoordinate), (float) ((blockWidth / 2) + yCoordinate));
        }
    }

    @Override
    public void move() {
        if (exist() && yCoordinate <= Main.windowLength + 5){
            setYCoordinate(getYCoordinate() + speedY);
        }
    }

    public void loseHealth(double damage) {
        Health -= damage;
    }

    // getter & setter

    public static double getHealth() {
        return Health;
    }

    public static void setHealth(double Health) {
        Block.Health = Health;
    }

    public static int getEXP() {
        return EXP;
    }

    public static void setEXP(int EXP) {
        Block.EXP = EXP;
    }

    public static double getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(double speedY) {
        Block.speedY = speedY;
    }

}
