package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Interfaces.Shootable;

public class Shooter extends Item implements Movable, Shootable {
    private int HP = 3;
    private int Max_EXP = 5;
    private int current_EXP = 0;

    private int Level = 1;
    private int LevelUpPointer = 0;

    private double shotSpeed = -1;
    private double shotPower = 1;

    public Shooter(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        shoot();
    }

    @Override
    public void shoot() {
        Thread thread = new Thread(() -> {
            // does something
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void move(double xCoordinate, double yCoordinate) {
        // does something
    }

    private void addLevel() {
        Level++;
        addHP();
        addMax_EXP();
        addLevelUpPointer();
    }

    public void addHP() {
        HP++;
    }

    private void addMax_EXP() {
        Max_EXP = (int) (Max_EXP * 1.5);
    }

    private void addLevelUpPointer() {
        LevelUpPointer++;
    }

    public void addShotSpeed() {
        this.shotSpeed += 0.5;
    }

    public void addShotPower() {
        this.shotPower += 0.5;
        Bullet.setPower(shotPower);
    }

    // getter & setter

    public int getHP() {
        return HP;
    }

    public int getMax_EXP() {
        return Max_EXP;
    }

    public int getCurrent_EXP() {
        return current_EXP;
    }

    public void setCurrent_EXP(int current_EXP) {
        this.current_EXP = current_EXP;
    }

    public int getLevel() {
        return Level;
    }

    public int getLevelUpPointer() {
        return LevelUpPointer;
    }

    public double getShotSpeed() {
        return shotSpeed;
    }

    public double getShotPower() {
        return shotPower;
    }

}
