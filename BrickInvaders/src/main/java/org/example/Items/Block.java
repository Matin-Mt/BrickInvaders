package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;

public class Block extends Item implements Movable {
    private double Health = 2;
    private int EXP = 1;
    public static int speedY = 30;

    public static int blockWidth = 15;
    public static int blockLength = 50;

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



    public void show(int x , int y) {


    }

    @Override
    public void move() {
        for (Block c :
             Main.blocks) {
            c.setYCoordinate(c.getYCoordinate()+2.0);
        }
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

    public static int getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(int speedY) {
        Block.speedY = speedY;
    }

}
