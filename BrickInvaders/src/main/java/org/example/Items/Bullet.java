package org.example.Items;

import org.example.Interfaces.Movable;

public class Bullet extends Item implements Movable {
    /*
    * isPoison:
    * false -> it has been shot by Shooter, and is not poisonous
    * true -> it has been shot by Boss, and it is Poisonous
    * */

    private boolean isPoison = false;
    private final int ySpeed = -3;
    private static double power;


    public Bullet(int xCoordinate, int yCoordinate, double power) {
        super(xCoordinate, yCoordinate);
        Bullet.power = power;
        move(xCoordinate, yCoordinate);
    }

    public Bullet(int xCoordinate, int yCoordinate, double power, boolean isPoison) {
        super(xCoordinate, yCoordinate);
        Bullet.power = power;
        this.isPoison = isPoison;
        move(xCoordinate, yCoordinate);
    }

    @Override
    public void show() {

    }

    @Override
    public void move(double xCoordinate, double yCoordinate) {
        // does something
        show();
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

    public void setPoison(boolean poison) {
        isPoison = poison;
    }

    public boolean isPoison() {
        return isPoison;
    }

}
