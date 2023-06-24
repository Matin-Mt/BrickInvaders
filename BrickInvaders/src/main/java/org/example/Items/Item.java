package org.example.Items;

public class Item {
    protected boolean exist = true;
    protected double xCoordinate;
    protected double yCoordinate;

    public Item(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }


    public boolean exist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
