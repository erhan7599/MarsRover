package com.infonal;


  //Enum representing basic directions like left, right and middle (or move)

public enum Direction {
    L(-1), M(0), R(1);

    private int value;

    private Direction(int pValue) {
        value = pValue;
    }

    public int getValue() {
        return value;
    }
}
