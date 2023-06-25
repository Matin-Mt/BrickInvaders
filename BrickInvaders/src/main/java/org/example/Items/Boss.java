package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;
import processing.core.PApplet;

public class Boss extends Item implements Movable {
    private final static PApplet a = Main.applet;

    private static double Health = 20;
    private static int EXP = 15;

    private double ySpeed = 0.3;
    private double xSpeed = 0.6;

    private double bossWidth = Main.windowWidth / 2d;
    private double bossLength = 80;

    public Boss(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public void healthChecking() {
        if (exist() && (yCoordinate - (bossLength / 2)) < Main.windowLength) {
            if (Health <= 0) {
                setExist(false);
            }
            if (yCoordinate >= Main.windowLength + (bossLength / 2)) {
                setExist(false);
            }
        }
    }

    @Override
    public void show() {
        if (exist() && (getYCoordinate() - (bossLength / 2)) < Main.windowLength) {
            a.fill(174, 255, 0);
            a.arc((float) getXCoordinate(), (float) getYCoordinate(), (float) bossWidth, (float) bossLength, a.PI, 2 * a.PI);
            a.fill(255, 0, 0);
            a.textAlign(a.CENTER);
            a.textSize(14);
            a.text(Double.toString(getHealth()), (float) getXCoordinate(), (float) getYCoordinate() - 20);
        }
    }

    @Override
    public void move() {
        if (exist() && yCoordinate <= Main.windowLength){
            setXCoordinate(getXCoordinate() + xSpeed);
            setYCoordinate(getYCoordinate() + ySpeed);

            if ((getXCoordinate() + (bossWidth / 2)) >= Main.windowWidth)
                setXSpeed(-1 * xSpeed);
            if (getXCoordinate() - (bossWidth / 2) <= 0) {
                setXSpeed(-1 * xSpeed);
            }
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
        Boss.Health = Health;
    }

    public static int getEXP() {
        return EXP;
    }

    public static void setEXP(int EXP) {
        Boss.EXP = EXP;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getBossWidth() {
        return bossWidth;
    }

    public double getBossLength() {
        return bossLength;
    }
}
