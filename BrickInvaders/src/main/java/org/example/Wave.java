package org.example;

import org.example.Items.Block;
import org.example.Items.Boss;
import processing.core.PApplet;

import java.util.ArrayList;

public class Wave {
    private static PApplet applet = Main.applet;
    Wave wave;

    private boolean exist = true;
    private static int waveLevel = 1;
    private static int blockNumber = 12;

    private ArrayList<Block> waveBlocks;
    private Boss boss;

    public Wave() {
        wave = this;
        waveBlocks = new ArrayList<>(blockNumber);
        createBlocks();
        waveExist();
    }

    private void levelWave() {
        exist = true;
        waveLevel++;
        blockNumberUpdate();
        wave = new Wave();
    }

    private void blockNumberUpdate() {
        blockNumber = (int) (waveLevel * blockNumber * 1.2);
    }

    private void createBlocks() {
        if (waveLevel % 5 != 0) {
            int blocksPerRow = 4;
            int lowYCoordinate = - Block.blockLength;
            final int highXCoordinate = Main.windowWidth / blocksPerRow;

            int lowCoordinate = 0;
            int highCoordinate = highXCoordinate;

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
            boss = new Boss(Main.windowWidth, -50);
        }
    }

    private void waveExist() {
        if (waveLevel % 5 != 0) {
            if (waveBlocks != null) {
                for (var b : waveBlocks) {
                    if (b.exist() && b.getXCoordinate() >= Main.windowLength) {
                        exist = false;
                    }
                }
                if (exist)
                    waveExist();
            }
            try {
                waitWave();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (boss != null && boss.exist()) {
            waveExist();

        }
        levelWave();
    }

    private void waitWave() throws InterruptedException {
        // show texts

        Thread.sleep(5000);
    }
}
