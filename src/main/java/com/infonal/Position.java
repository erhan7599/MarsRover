package com.infonal;


 //Class representing rover's position.
 
public class Position {

    private Coordinates coordinates;

    private CardinalDirection cardinalDirection;

    public Position(int pX, int pY, CardinalDirection pCardinalDirection) {
        coordinates = new Coordinates(pX, pY);
        cardinalDirection = pCardinalDirection;
    }

    public int getX() {
        return coordinates.getX();
    }

    public void setX(int pX) {
        coordinates.setX(pX);
    }

    public int getY() {
        return coordinates.getY();
    }

    public void setY(int pY) {
        coordinates.setY(pY);
    }

    public CardinalDirection getCardinalDirection() {
        return cardinalDirection;
    }

    public void setCardinalDirection(CardinalDirection pCardinalDirection) {
        cardinalDirection = pCardinalDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (!coordinates.equals(position.coordinates)) return false;
        if (cardinalDirection != position.cardinalDirection) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coordinates.hashCode();
        result = 31 * result + cardinalDirection.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Position{"
                + "coordinates=" + coordinates
                + ", cardinalDirection=" + cardinalDirection
                + '}';
    }
}
