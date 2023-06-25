package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;
import processing.core.PApplet;

public class Boss extends Item implements Movable {
    private final static PApplet a = Main.applet;

    private double Health = 20;
    private int EXP = 15;

    private double ySpeed = 0.3;
    private double xSpeed = 0.6;

    private double bossWidth = Main.windowWidth / 2d;
    private double bossLength = 80;

    public Boss(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        healthChecking();
    }

    private void healthChecking() {
        Thread thread = new Thread(() -> {
            while (exist() && (yCoordinate - (bossLength / 2)) < Main.windowLength) {
                if (Health <= 0)
                    setExist(false);

                if (yCoordinate >= Main.windowLength + (bossLength / 2))
                    setExist(false);
            }
        });
        thread.start();
    }

    @Override
    public void show() {
        if (exist() && (getYCoordinate() - (bossLength / 2)) < Main.windowLength) {
            a.fill(0);
            a.arc((float) getXCoordinate(), (float) getYCoordinate(), (float) bossWidth, (float) bossLength, a.PI, 2 * a.PI);
            a.fill(255);
            a.textAlign(a.CENTER);
            a.textSize(16);
            a.text(Double.toString(getHealth()), (float) getXCoordinate(), (float) getYCoordinate() - 20);
        }
    }

    @Override
    public void move() {
        Thread thread = new Thread(() -> {
            while (exist() && getYCoordinate() - (bossLength / 2) < Main.windowLength) {
                setXCoordinate(getXCoordinate() + xSpeed);
                setYCoordinate(getYCoordinate() + ySpeed);

                if ((getXCoordinate() + (bossWidth / 2)) >= Main.windowWidth)
                    setXSpeed(-1 * xSpeed);
                if (getXCoordinate() - (bossWidth / 2) <= 0) {
                    setXSpeed(-1 * xSpeed);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
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
