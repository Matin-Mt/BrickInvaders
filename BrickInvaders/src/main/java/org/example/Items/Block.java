package org.example.Items;

import org.example.Interfaces.Movable;

public class Block extends Item implements Movable {
    private double Health = 2;
    private int EXP = 1;

    public static int blockWidth = 20;
    public static int blockLength = 60;

    public Block(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        healthChecking();
    }

    private void healthChecking() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Health <= 0) {
                    setExist(false);
                    move();
                }
            }
        });
        thread.start();
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

}
