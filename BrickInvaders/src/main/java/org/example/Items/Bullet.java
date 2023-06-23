package org.example.Items;

import org.example.Interfaces.Movable;

public class Bullet extends Item implements Movable {
    private final int ySpeed = -3;
    private static double power;

    public Bullet(int xCoordinate, int yCoordinate, double power) {
        super(xCoordinate, yCoordinate);
        Bullet.power = power;
        move(xCoordinate, yCoordinate);
    }

    @Override
    public void show() {

    }

    @Override
    public void move(int xCoordinate, int yCoordinate) {
        // does something
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public static void setPower(double power) {
        Bullet.power = power;
    }

    public double getPower() {
        return power;
    }

}
