package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;
import processing.core.PApplet;

public class Block extends Item implements Movable {
    public static PApplet a = Main.applet;

    private double Health = 2;
    private int EXP = 1;
    public static double speedY = 1;

    public static int blockWidth = 15;
    public static int blockLength = 50;

    public Block(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    private void healthChecking() {
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

    public double getHealth() {
        return Health;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public static double getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(double speedY) {
        Block.speedY = speedY;
    }

}
