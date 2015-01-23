package com.infonal;


  //Class representing the Plateau to be explored.

public class Plateau {
    private Coordinates origin;

    private Coordinates rightTop;

    private Coordinates rightBottom;

    private Coordinates leftTop;

    public Plateau(int x, int y) {
        origin = new Coordinates(0, 0);
        rightTop = new Coordinates(x, y);
        rightBottom = new Coordinates(x, 0);
        leftTop = new Coordinates(0, y);
    }

    public Plateau(Coordinates pCoordinates) {
        origin = new Coordinates(0, 0);
        rightTop = pCoordinates;
        rightBottom = new Coordinates(pCoordinates.getX(), 0);
        leftTop = new Coordinates(0, pCoordinates.getY());
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public void setOrigin(Coordinates pOrigin) {
        origin = pOrigin;
    }

    public Coordinates getRightTop() {
        return rightTop;
    }

    public void setRightTop(Coordinates pRightTop) {
        rightTop = pRightTop;
    }

    public Coordinates getRightBottom() {
        return rightBottom;
    }

    public void setRightBottom(Coordinates pRightBottom) {
        rightBottom = pRightBottom;
    }

    public Coordinates getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(Coordinates pLeftTop) {
        leftTop = pLeftTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plateau)) return false;

        Plateau plateau = (Plateau) o;

        if (!leftTop.equals(plateau.leftTop)) return false;
        if (!origin.equals(plateau.origin)) return false;
        if (!rightBottom.equals(plateau.rightBottom)) return false;
        if (!rightTop.equals(plateau.rightTop)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = origin.hashCode();
        result = 31 * result + rightTop.hashCode();
        result = 31 * result + rightBottom.hashCode();
        result = 31 * result + leftTop.hashCode();
        return result;
    }
}
