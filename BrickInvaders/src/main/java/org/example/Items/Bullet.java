package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;

public class Bullet extends Item implements Movable {
    private final double ySpeed = -3;
    private static double power;

    public Bullet(double xCoordinate, double yCoordinate, double power) {
        super(xCoordinate, yCoordinate);
        Bullet.power = power;
        move();
    }

    @Override
    public void show() {
        Main.applet.circle(200,600,15);

    }

    @Override
    public void move() {
        setYCoordinate(getYCoordinate()-10);

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
