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


    public static void makeBlock(){
        for (int i = 0; i <3 ; i++) {
            Main.blocks.add(new Block(20,speedY));
            Main.blocks.add(new Block(80,speedY));
            Main.blocks.add(new Block(140,speedY));
            Main.blocks.add(new Block(200,speedY));
            Main.blocks.add(new Block(260,speedY));
            Main.blocks.add(new Block(320,speedY));
            speedY  += 30;

        }
    }
    public void show() {

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
