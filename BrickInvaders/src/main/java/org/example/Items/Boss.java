package org.example.Items;

import org.example.Interfaces.Killable;
import org.example.Interfaces.Movable;
import org.example.Main;
import org.example.Wave;
import processing.core.PApplet;

public class Boss extends Item implements Movable, Killable {
    private final static PApplet a = Main.applet;

    private static double Health = 70;
    private double HP = Health;
    private static int EXP = 15;

    private double ySpeed = 0.3;
    private double xSpeed = 0.6;

    private double bossWidth = Main.windowWidth / 2d;
    private double bossLength = 80;

    public Boss(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public void healthChecking() {
        if (exist() && (yCoordinate - (bossLength / 2)) < Main.windowLength) {
            if (HP <= 0) {
                setExist(false);
                Wave.shooter.setCurrent_EXP(Wave.shooter.getCurrent_EXP() + getEXP());
                Wave.shooter.addScore(getEXP());
            }
            if (yCoordinate >= Main.windowLength + (bossLength / 2)) {
                setExist(false);
            }
        }
    }

    @Override
    public void show() {
        if (exist() && (getYCoordinate() - (bossLength / 2)) < Main.windowLength) {
            if (HP <= 0.25 * Health) {
                a.fill(245, 5, 5);
            } else if (HP <= 0.5 * Health) {
                a.fill(250, 246, 2);
            } else {
                a.fill(3, 61, 252);
            }
            a.arc((float) getXCoordinate(), (float) getYCoordinate(), (float) bossWidth, (float) bossLength, a.PI, 2 * a.PI);
            a.fill(0);
            a.textAlign(a.CENTER);
            a.textSize(14);
            a.text(Double.toString(getHP()), (float) getXCoordinate(), (float) getYCoordinate() - 20);
        }
    }

    @Override
    public void move() {
        if (exist() && yCoordinate <= Main.windowLength){
            setXCoordinate(getXCoordinate() + xSpeed);
            setYCoordinate(getYCoordinate() + ySpeed);

            if ((getXCoordinate() + (bossWidth / 2)) >= Main.windowWidth)
                setXSpeed(-1 * xSpeed);
            if (getXCoordinate() - (bossWidth / 2) <= 0) {
                setXSpeed(-1 * xSpeed);
            }
        }
    }

    public void loseHP(double damage) {
        HP -= damage;
    }

    // getter & setter

    public static double getHealth() {
        return Health;
    }

    public static void setHealth(double Health) {
        Boss.Health = Health;
    }

    public static int getEXP() {
        return EXP;
    }

    public static void setEXP(int EXP) {
        Boss.EXP = EXP;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getBossWidth() {
        return bossWidth;
    }

    public double getBossLength() {
        return bossLength;
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
                    if (b.getXCoordinate() + 15 >= getXCoordinate() - (bossWidth / 2) && b.getXCoordinate() - 15 <= getXCoordinate() + (bossWidth / 2)) {
                        if (b.getYCoordinate() - 15 <= getYCoordinate() && b.getYCoordinate() - 15 >= getYCoordinate() - 15 ) {
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
            if (getYCoordinate() >= s.getYCoordinate() - 85 && getYCoordinate() - (bossLength / 2) <= s.getYCoordinate()) {
                if (getXCoordinate() - (bossWidth / 2) <= s.getXCoordinate() + 18 && getXCoordinate() + (bossWidth / 2) >= s.getXCoordinate() - 18) {
                    setExist(false);
                    if (!Shooter.shielded){
                        s.loseHP();
                    }
                }
            }
        }
    }
}
