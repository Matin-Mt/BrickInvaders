package org.example;

import org.example.Items.Block;
import org.example.Items.Boss;
import org.example.Items.Bullet;
import org.example.Items.Shooter;
import processing.core.PApplet;

import java.util.ArrayList;

public class Wave {
    private static PApplet a = Main.applet;
    Wave wave;
    public static ArrayList<Bullet> bullets = new ArrayList<>();

    public static int waveLevel = 1;
    private static int blockNumber = 12;
    private int Number = 10;

    public static Shooter shooter;
    private ArrayList<Block> waveBlocks;
    private Boss boss;

    public Wave(Shooter shooter) {
        wave = this;
        Wave.shooter = shooter;
        waveBlocks = new ArrayList<>();
        createBlocks();
    }

    public void collide() {
        if (waveBlocks != null) {
            for (var b: waveBlocks) {
                if (b.exist()){
                    b.bulletCollide();
                }
            }
        }
        if (boss != null) {
            boss.bulletCollide();
        }
    }

    public void healthChecking() {
        if (bullets != null) {
            for (var b: bullets) {
                if (b.exist()){
                    b.healthChecking();
                }
            }
        }
        if (waveBlocks != null) {
            for (var b: waveBlocks) {
                b.healthChecking();
            }
        }
        if (boss != null) {
            boss.healthChecking();
        }
    }

    public void show() {
        if (bullets != null) {
            for (var b: bullets) {
                if (b.exist()){
                    b.show();
                }
            }
        }
        shooter.show();
        if (waveBlocks != null) {
            for (var b: waveBlocks) {
                if (b.exist()){
                    b.show();
                }
            }
        }
        if (boss != null) {
            if (boss.exist()){
                boss.show();
            }
        }
    }

    public void move() {
        shooter.move();
        if (bullets != null) {
            for (var b: bullets) {
                if (b.exist()){
                    b.move();
                }
            }
        }
        if (waveBlocks != null) {
            for (var b: waveBlocks) {
                if (b.exist()){
                    b.move();
                }
            }
        }
        if (boss != null) {
            if (boss.exist()){
                boss.move();
            }
        }
    }

    public void levelWave() {
        waveLevel++;
        blockUpdate();
        createBlocks();
    }

    private void blockUpdate() {
        blockNumber = waveLevel * Number;
        Block.setHealth(Block.getHealth() + 1);
        Block.setEXP(Block.getEXP() + 1);
        if (waveLevel % 5 == 1) {
            Boss.setHealth(Boss.getHealth() + 20);
            Boss.setEXP(Boss.getEXP() + 15);
        }
    }

    private void createBlocks() {
        if (waveLevel % 5 != 0) {
            waveBlocks = new ArrayList<>();
            int blocksPerRow = 4;
            int lowYCoordinate = - Block.blockWidth - 30;
            final int highXCoordinate = Main.windowLength / blocksPerRow;

            int lowCoordinate = 0;
            int highCoordinate = highXCoordinate - 20;

            for (int i = 1; i < blockNumber; i++) {
                waveBlocks.add(new Block((int) a.random(lowCoordinate, highCoordinate), (int) a.random(lowYCoordinate - 30, lowYCoordinate + 30)));

                lowCoordinate += highXCoordinate;
                highCoordinate += highXCoordinate;

                if (highCoordinate > Main.windowWidth) {
                    lowCoordinate = 0;
                    highCoordinate = highXCoordinate;
                    lowYCoordinate -= (Block.blockWidth + 70);
                }
            }

        } else {
            boss = new Boss(Main.windowWidth / 2f, 0);
        }
    }

    public boolean exist() {
        boolean exist = false;

        if (waveLevel % 5 != 0) {
            if (waveBlocks != null) {
                for (var b : waveBlocks) {
                    if (b.exist() && b.getYCoordinate() < Main.windowLength - 10) {
                        exist = true;
                        break;
                    }
                }
            }

        } else if (boss != null) {
            if (boss.exist() && boss.getYCoordinate() < Main.windowLength) {
                exist = true;
            }
        }

        return exist;
    }

    public int getLevel() {
        return waveLevel;
    }
}
