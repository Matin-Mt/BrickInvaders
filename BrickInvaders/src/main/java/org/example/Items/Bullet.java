package org.example.Items;

import org.example.Interfaces.Movable;

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

    }

    @Override
    public void move() {
        // does something
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
