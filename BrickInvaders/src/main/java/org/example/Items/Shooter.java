package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Interfaces.Shootable;
import org.example.Main;
import org.example.Wave;
import processing.core.PApplet;

public class Shooter extends Item implements Movable, Shootable {
    private static PApplet a = Main.applet;

    private int HP = 3;
    private int Max_EXP = 5;
    private int current_EXP = 0;

    private int Level = 1;
    private int LevelUpPointer = 0;

    private static double shotSpeed = 1;
    private static double shotPower = 1;

    public Shooter(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        shoot();
    }

    @Override
    public void shoot() {
        Thread thread = new Thread(() -> {
            while (exist()) {
                Wave.bullets.add(new Bullet(xCoordinate, getYCoordinate(), shotPower));
                try {
                    Thread.sleep((long) (1000 / shotSpeed));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void show() {
        a.fill(105, 104, 99);
        // body
        a.arc((float) xCoordinate, (float) (yCoordinate - 25), 36, 20, 0, a.PI);
        a.rect((float) (xCoordinate - 18), (float) (yCoordinate - 85), 36, 60);
        // tire
        a.fill(0);
        a.circle((float) xCoordinate, (float) (yCoordinate - 15),30);
        a.fill(0, 255, 255);
        a.circle((float) xCoordinate, (float) (yCoordinate - 15), 20);
        a.fill(0);
        a.circle((float) xCoordinate, (float) (yCoordinate - 15),5);
        a.stroke(0);
        a.strokeWeight(5);
        a.line((float) (xCoordinate - 10), (float) (yCoordinate - 15), (float) (xCoordinate + 10), (float) (yCoordinate - 15));
        a.line((float) xCoordinate, (float) (yCoordinate - 25), (float) xCoordinate, (float) (yCoordinate + 25));
        a.noStroke();
    }

    @Override
    public void move() {
        setXCoordinate(a.mouseX);
    }

    public void checkLevelUp() {
        if (current_EXP >= Max_EXP){
            Level++;
            setCurrent_EXP(0);
            addMax_EXP();
            addLevelUpPointer();
        }
    }

    public void loseHP() {
        HP--;
    }

    public void addHP() {
        HP++;
        LevelUpPointer -= 1;
    }

    private void addMax_EXP() {
        Max_EXP = (int) (Max_EXP * 1.5);
    }

    private void addLevelUpPointer() {
        LevelUpPointer++;
    }

    public void addShotSpeed() {
        Shooter.shotSpeed += 0.5;
        LevelUpPointer -= 1;
    }

    public void addShotPower() {
        Shooter.shotPower += 0.5;
        Bullet.setPower(shotPower);
        LevelUpPointer -= 1;
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
