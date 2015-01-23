package com.infonal;

public class Rover {

    private Position position;

    private Plateau plateau;

    /**
     * Convenience constructor to initialize with Coordinates and geographic
     * direction.
     * @param x
     * @param y
     * @param pCardinalDirection
     */
    public Rover(int x, int y, CardinalDirection pCardinalDirection) {
        position = new Position(x, y, pCardinalDirection);
    }

    /**
     * Convenience constructor to initialize Rover with position.
     * @param pPosition
     */
    public Rover(Position pPosition) {
        position = pPosition;
    }

    /**
     * Gets the current position of the rover.
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position. It is private as it can be set by only command
     * processing.
     * @param pX
     * @param pY
     * @param pCardinalDirection
     */
    private void setPosition(int pX, int pY, CardinalDirection pCardinalDirection) {
        position.setX(pX);
        position.setY(pY);
        position.setCardinalDirection(pCardinalDirection);
    }

    /**
     * Method to change the direction of the rover.
     * @param pCardinalDirection
     */
    private void turn(CardinalDirection pCardinalDirection) {
        position.setCardinalDirection(pCardinalDirection);
    }

    /**
     * Method moves the rover one click further. Can be separated into an
     * individual class whose job is moving the rover, like engine.
     */
    private void moveForward() {
        CardinalDirection cardinalDirection = position.getCardinalDirection();
        int x = position.getX();
        int y = position.getY();
        if (cardinalDirection.equals(CardinalDirection.N)
                && inNorthernBoundaries()) {
            setPosition(x, y + 1, cardinalDirection);
        } else if (cardinalDirection.equals(CardinalDirection.S)
                && inSoutherBoundaries()){
            setPosition(x, y - 1, cardinalDirection);
        } else if (cardinalDirection.equals(CardinalDirection.E)
                && inEastBoundaries()) {
            setPosition(x + 1, y, cardinalDirection);
        } else if (cardinalDirection.equals(CardinalDirection.W)
                && inWestBoundaries()) {
            setPosition(x - 1, y, cardinalDirection);
        }
    }

    /**
     * Method processes given command. Only this method knows how to process
     * a given command. May be separated to a class whose specialty is
     * processing.
     * @param pDirection
     */
    private void processCommand(Direction pDirection) {
        if (pDirection.equals(Direction.M)) {
            this.moveForward();
        } else {
            turn(calculateDirection(pDirection));
        }
    }

    /**
     * Calculates the new direction. May be seperated to a class whose specialty
     * is processing.
     * @param pDirection
     * @return
     */
    private CardinalDirection calculateDirection(Direction pDirection) {
        int currentDirection = getPosition().getCardinalDirection().ordinal();
        int turnValue = pDirection.getValue();
        int newDirection = (currentDirection + turnValue + 4) % 4;
        return (CardinalDirection.values()[newDirection]);
    }

    /**
     * Method to be used by NASA to send commands to rover.
     * @param pDirection
     */
    public void sendCommand(Direction pDirection) {
        processCommand(pDirection);
    }

    /**
     * Rovers need to be aware of the boundary of their surroundings.
     * @param pPlateau
     */
    public void setPlateau(Plateau pPlateau) {
        plateau = pPlateau;
    }

    /**
     * Checks if rover is within western boundaries
     * @return
     */
    private boolean inWestBoundaries() {
        boolean status = true;
        if (position.getX() <= 0) {
            status = false;
        }
        return status;
    }

    /**
     * Checks if rover is within eastern boundaries
     * @return
     */
    private boolean inEastBoundaries() {
        boolean status = true;
        if (position.getX() >= plateau.getRightTop().getX()) {
            status = false;
        }
        return status;
    }

    /**
     * Checks if rover is within northern boundaries
     * @return
     */
    private boolean inNorthernBoundaries() {
        boolean status = true;
        if (position.getY() >= plateau.getRightTop().getY()) {
            status = false;
        }
        return status;
    }

    /**
     * Checks if rover is within southern boundaries
     * @return
     */
    private boolean inSoutherBoundaries() {
        boolean status = true;
        if (position.getY() <= 0) {
            status = false;
        }
        return status;
    }
}
