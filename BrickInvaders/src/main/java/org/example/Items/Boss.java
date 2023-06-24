package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Interfaces.Shootable;

public class Boss extends Item implements Movable, Shootable {
    private double Health = 50;
    private int EXP = 15;

    private double ySpeed = 0.6;

    public Boss(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        move();
        shoot();
    }

    @Override
    public void shoot() {
        Thread thread = new Thread(() -> {
            // does something
        });
        try {
            thread.wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void move() {
        // does something
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
}
