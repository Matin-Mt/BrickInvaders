package org.example.Items;

import org.example.Interfaces.Killable;
import org.example.Interfaces.Movable;
import org.example.Main;
import org.example.Wave;
import processing.core.PApplet;

public class Block extends Item implements Movable, Killable {
    public static PApplet a = Main.applet;

    private static double Health = 2;
    private double HP = Health;
    private static int EXP = 1;
    public static double speedY = 1;

    public static int blockWidth = 15;
    public static int blockLength = 50;

    public Block(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public void healthChecking() {
        if (exist()) {
            if (HP <= 0) {
                setExist(false);
                Wave.shooter.setCurrent_EXP(Wave.shooter.getCurrent_EXP() + getEXP());
                Wave.shooter.addScore(getEXP());
            }

            if (getYCoordinate() >= Main.windowLength) {
                setExist(false);
            }
        }
    }

    @Override
    public void show() {
        if (exist() && yCoordinate < Main.windowLength - 50){
            a.fill(174, 255, 0);
            a.rect((float) getXCoordinate(), (float) getYCoordinate(), blockLength, blockWidth);
            a.fill(255, 0, 0);
            a.textAlign(a.CENTER);
            a.textSize(14);
            a.text(Double.toString(getHP()), (float) ((blockLength / 2) + xCoordinate), (float) ((blockWidth / 2) + yCoordinate));
        }
    }

    @Override
    public void move() {
        if (exist() && yCoordinate <= Main.windowLength + 5){
            setYCoordinate(getYCoordinate() + speedY);
        }
    }

    public void loseHP(double damage) {
        setHP(HP - damage);
    }

    // getter & setter

    public static double getHealth() {
        return Health;
    }

    public static void setHealth(double Health) {
        Block.Health = Health;
    }

    public static int getEXP() {
        return EXP;
    }

    public static void setEXP(int EXP) {
        Block.EXP = EXP;
    }

    public static double getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(double speedY) {
        Block.speedY = speedY;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    @Override
    public void bulletCollide() {
        if (Wave.bullets != null) {
            for (var b: Wave.bullets) {
                if (b.exist() && exist()) {
                    if (yCoordinate <= b.getYCoordinate() - 15 && yCoordinate + blockWidth >= b.getYCoordinate() - 15) {
                        if (b.getXCoordinate() + 15 >= getXCoordinate() && b.getXCoordinate() - 15 <= getXCoordinate() + blockLength) {
                            b.setExist(false);
                            loseHP(b.getPower());
                            Wave.bullets.remove(b);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void shooterCollide() {
        Shooter s = Main.shooter;
        if (exist() && s.getHP() > 0) {
            if (getXCoordinate() <= s.getXCoordinate() + 18 && getXCoordinate() + blockLength >= s.getXCoordinate() - 18) {
                if (getYCoordinate() + blockWidth >= s.getYCoordinate() - 85 && getYCoordinate() <= s.getYCoordinate()) {
                    setExist(false);
                    s.loseHP();
                }
            }
        }
    }
}
