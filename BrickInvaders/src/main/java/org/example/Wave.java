package org.example;

import org.example.Items.Block;
import org.example.Items.Boss;
import org.example.Items.Shooter;
import processing.core.PApplet;

import java.util.ArrayList;

public class Wave {
    private static PApplet a = Main.applet;
    Wave wave;

    private static int waveLevel = 1;
    private static int blockNumber = 12;

    private static Shooter shooter;
    private ArrayList<Block> waveBlocks;
    private Boss boss;

    public Wave(Shooter shooter) {
        wave = this;
        Wave.shooter = shooter;
        waveBlocks = new ArrayList<>();
        createBlocks();
    }

    public void show() {
        if (waveBlocks != null) {
            for (var b: waveBlocks) {
                b.show();
            }
        }
        if (boss != null) {
            boss.show();
        }
    }

    public void move() {
        if (waveBlocks != null) {
            for (var b: waveBlocks) {
                b.move();
            }
        }
        if (boss != null) {
            boss.move();
        }
    }

    private void levelWave() {
        for (int i = 0; i < 600; i++) {
            a.fill(255, 0, 0);
            a.textSize(35);
            a.text("Wave", 200, 200);
        }
        waveLevel++;
        blockNumberUpdate();
        wave = new Wave(Wave.shooter);
    }

    private void blockNumberUpdate() {
        blockNumber = (int) (waveLevel * blockNumber * 1.2);
    }

    private void createBlocks() {
        if (waveLevel % 5 != 0) {
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

    public void waveExist() {
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
            exist = boss.exist() && !(boss.getYCoordinate() - (boss.getBossLength() / 2) >= Main.windowLength);
        }

        if (exist) {
            move();
            show();

        } else {
            levelWave();
        }

    }

    public Shooter getShooter() {
        return shooter;
    }
}
