package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;
import org.example.Wave;
import processing.core.PApplet;

public class Bullet extends Item implements Movable {
    private static PApplet a = Main.applet;

    private final double ySpeed = -3;
    private static double power;

    public Bullet(double xCoordinate, double yCoordinate, double power) {
        super(xCoordinate, yCoordinate);
        Bullet.power = power;
    }

    public void healthChecking() {
        if (!exist() || yCoordinate < 0) {
            setExist(false);
            Wave.bullets.remove(Bullet.this);
        }
    }

    @Override
    public void show() {
        a.fill(255, 0 ,0);
        a.circle((float) xCoordinate,(float) yCoordinate,15);

    }

    @Override
    public void move() {
        setYCoordinate(yCoordinate + ySpeed);

    }

    public double getYSpeed() {
        return ySpeed;
    }

    public static void setPower(double power) {
        Bullet.power = power;
    }

    public double getPower() {
        return power;
    }

}