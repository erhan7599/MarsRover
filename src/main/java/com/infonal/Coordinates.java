package com.infonal;


	//Class representing point Cartesian coordinate system.
 
public class Coordinates {
    int x;
    int y;

    public Coordinates(int pX, int pY) {
        x = pX;
        y = pY;
    }

    public int getX() {
        return x;
    }

    public void setX(int pX) {
        x = pX;
    }

    public int getY() {
        return y;
    }

    public void setY(int pY) {
        y = pY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
