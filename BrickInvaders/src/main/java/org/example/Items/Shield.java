package org.example.Items;

import org.example.Interfaces.Movable;
import org.example.Main;
import processing.core.PApplet;

public class Shield extends Item implements Movable {
    private static PApplet a = Main.applet;

    private int duration = 5;

    public Shield(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    @Override
    public void show() {
        if (exist()){
            a.fill(200, 222, 31);
            a.circle((float) xCoordinate, (float) yCoordinate, 5);
        }
    }

    @Override
    public void move() {
        if (exist()) {
            setYCoordinate(yCoordinate + 1);
        }
    }

    public void shooterCollide() {
        Shooter s = Main.shooter;
        if (exist() && s.getHP() > 0) {
            if (s.getXCoordinate() + 18 >= getXCoordinate() && s.getXCoordinate() - 18 <= getXCoordinate()) {
                if (s.getYCoordinate() - 85 <= getYCoordinate() && s.getYCoordinate() >= getYCoordinate()) {
                    setExist(false);
                    activate();
                }
            }
        }
    }

    private void activate() {
        Thread thread = new Thread(() -> {
            Shooter.shielded = true;
            try {
                Thread.sleep(duration * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Shooter.shielded = false;
        });
        thread.start();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
