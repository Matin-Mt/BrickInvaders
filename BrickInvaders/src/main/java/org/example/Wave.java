package org.example;

import org.example.Items.Block;
import org.example.Items.Boss;
import org.example.Items.Shooter;
import processing.core.PApplet;

import java.util.ArrayList;

public class Wave {
    private static PApplet applet = Main.applet;
    Wave wave;

    private boolean exist = true;
    private static int waveLevel = 1;
    private static int blockNumber = 12;

    private static Shooter shooter;
    private ArrayList<Block> waveBlocks;
    private Boss boss;

    public Wave(Shooter shooter) {
        wave = this;
        Wave.shooter = shooter;
        waveBlocks = new ArrayList<>(blockNumber);
        createBlocks();
        waveExist();
        move();
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

    private void move() {
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
        exist = true;
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
            int lowYCoordinate = - Block.blockLength - 30;
            final int highXCoordinate = Main.windowWidth / blocksPerRow;

            int lowCoordinate = 0;
            int highCoordinate = highXCoordinate - 20;

            for (int i = 1; i < blockNumber; i++) {
                waveBlocks.add(new Block((int) applet.random(lowCoordinate, highCoordinate), (int) applet.random(lowYCoordinate - 30, lowYCoordinate + 30)));

                lowCoordinate += highXCoordinate;
                highCoordinate += highXCoordinate;

                if (highCoordinate > Main.windowWidth) {
                    lowCoordinate = 0;
                    highCoordinate = highXCoordinate;
                    lowYCoordinate -= (Block.blockLength + 70);
                }
            }

        } else {
            boss = new Boss(Main.windowWidth / 2f, 0);
        }
    }

    private void waveExist() {
        Thread thread = new Thread(() -> {
            while (true) {

                if (waveLevel % 5 != 0) {
                    if (waveBlocks != null) {
                        exist = false;

                        for (var b : waveBlocks) {
                            if (b.exist() && b.getYCoordinate() < Main.windowLength) {
                                exist = true;
                            }
                        }

                        if (!exist) {
                            try {
                                Thread.sleep(10000);
                                levelWave();
                                break;

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } else if (boss != null) {
                    if (!boss.exist() || boss.getYCoordinate() - (boss.getBossLength() / 2) >= Main.windowLength) {
                        exist = false;
                        try {
                            Thread.sleep(10000);
                            levelWave();
                            break;

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public Shooter getShooter() {
        return shooter;
    }
}
