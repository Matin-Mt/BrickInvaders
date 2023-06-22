package org.example.Items;

import org.example.Interfaces.Movable;

public class Block extends Item implements Movable {
    private boolean exist = true;
    private double Health = 2;
    private int EXP = 1;

    public Block(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        healthChecking();
    }

    private void healthChecking() {
        while (exist){
            Thread thread = new Thread(() -> {
                if (Health <= 0) {
                    setExist(false);
                    move(xCoordinate, yCoordinate);
                }
            });
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void move(double xCoordinate, double yCoordinate) {
        // does something
        show();
    }

    public void loseHealth(double damage) {
        Health -= damage;
    }

    // getter & setter

    public boolean exist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

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
