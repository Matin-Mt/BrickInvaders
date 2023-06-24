package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Interfaces.Shootable;
import org.example.Main;
import processing.core.PApplet;

public class Boss extends Item implements Movable, Shootable {
    private static PApplet a = Main.applet;

    private double Health = 20;
    private int EXP = 15;

    private double ySpeed = 0.3;
    private double xSpeed = 0.6;

    public Boss(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        healthChecking();
        move(xCoordinate, yCoordinate);
    }

    private void healthChecking() {
        Thread thread = new Thread(() -> {
            while (exist() && yCoordinate < Main.windowLength) {
                if (Health <= 0)
                    setExist(false);

                if (yCoordinate >= Main.windowLength)
                    setExist(false);
            }
        });
        thread.start();
    }

    @Override
    public void shoot() {

    }

    @Override
    public void show() {
        Thread thread = new Thread(() -> {


        });
        thread.start();
    }

    @Override
    public void move(double xCoordinate, double yCoordinate) {
        Thread thread = new Thread(() -> {
            while (exist() && yCoordinate < Main.windowLength) {
                setYCoordinate(yCoordinate + ySpeed);
                setXCoordinate(xCoordinate + xSpeed);

                if (getXCoordinate() > Main.windowWidth)
                    setXSpeed(-1 * xSpeed);
                if (getXCoordinate() < 0)
                    setXSpeed(-1 * xSpeed);
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
}
