package com.infonal;

import org.junit.Test;

import com.infonal.CardinalDirection;
import com.infonal.CommandProcessor;
import com.infonal.Coordinates;
import com.infonal.Direction;
import com.infonal.Lander;
import com.infonal.Plateau;
import com.infonal.Position;
import com.infonal.Rover;
import com.infonal.commands.Command;
import com.infonal.commands.DeployRover;
import com.infonal.commands.InitPlateau;
import com.infonal.commands.MoveRover;

import java.util.List;

import static org.junit.Assert.*;


public class MarsRoverTest {

    @Test
    public void testGetPosition() {
        Rover rover = new Rover(7, 8, CardinalDirection.S);
        Position expectedPosition = new Position(7, 8, CardinalDirection.S);
        Position actualPosition = rover.getPosition();
        assertEquals("two positions are not equal", expectedPosition,
                actualPosition);
    }

    @Test
    public void testSetPosition() {
        Rover rover = new Rover(1, 3, CardinalDirection.E);
        assertEquals("x axis point value is not as expected", 1, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 3, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.E, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testMoveRoverInBounds() {
        Rover rover = new Rover(1, 3, CardinalDirection.E);
        Plateau plateau = new Plateau(4, 4);
        rover.setPlateau(plateau);
        rover.sendCommand(Direction.M);
        assertEquals("x axis point value is not as expected", 2, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 3, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.E, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testMoveRoverOutOfNorthernBounds() {
        Rover rover = new Rover(1, 3, CardinalDirection.N);
        Plateau plateau = new Plateau(3, 3);
        rover.setPlateau(plateau);
        rover.sendCommand(Direction.M);
        assertEquals("x axis point value is not as expected", 1, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 3, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.N, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testMoveRoverOutOfSouthernBounds() {
        Rover rover = new Rover(2, 0, CardinalDirection.S);
        Plateau plateau = new Plateau(3, 3);
        rover.setPlateau(plateau);
        rover.sendCommand(Direction.M);
        assertEquals("x axis point value is not as expected", 2, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 0, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.S, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testMoveRoverOutOfEasternBounds() {
        Rover rover = new Rover(3, 1, CardinalDirection.E);
        Plateau plateau = new Plateau(3, 3);
        rover.setPlateau(plateau);
        rover.sendCommand(Direction.M);
        assertEquals("x axis point value is not as expected", 3, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 1, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.E, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testMoveRoverOutOfWesternBounds() {
        Rover rover = new Rover(0, 2, CardinalDirection.W);
        Plateau plateau = new Plateau(3, 3);
        rover.setPlateau(plateau);
        rover.sendCommand(Direction.M);
        assertEquals("x axis point value is not as expected", 0, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 2, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.W, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testTurnLeftWhenFacingNorth() {
        Rover rover = new Rover(2, 4, CardinalDirection.N);
        rover.sendCommand(Direction.L);
        assertEquals("x axis point value is not as expected", 2, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 4, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.W, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testTurnRightWhenFacingWest() {
        Rover rover = new Rover(4, 3, CardinalDirection.W);
        rover.sendCommand(Direction.R);
        assertEquals("x axis point value is not as expected", 4, rover
                .getPosition().getX());
        assertEquals("y axis point value is not as expected", 3, rover
                .getPosition().getY());
        assertEquals("direction is wrong", CardinalDirection.N, rover.getPosition()
                .getCardinalDirection());
    }

    @Test
    public void testPositionEquality() {
        Position actualPosition = new Position(2, 7, CardinalDirection.E);
        Position expectedPosition = new Position(2, 7, CardinalDirection.E);
        assertEquals("two positions are not equal", expectedPosition,
                actualPosition);
    }

    @Test
    public void testPositionUnequality() {
        Position actualPosition = new Position(2, 7, CardinalDirection.E);
        Position expectedPosition = new Position(3, 7, CardinalDirection.E);
        assertNotEquals("two positions are equal!", expectedPosition,
                actualPosition);
    }

    @Test
    public void testCoordinatesEquality() {
        Coordinates actualCoordinates = new Coordinates(8, 9);
        Coordinates expectedCoordinates = new Coordinates(8, 9);
        assertEquals("two coordinates are not equal", expectedCoordinates,
                actualCoordinates);
    }

    @Test
    public void testCoordinatesUnequality() {
        Coordinates actualCoordinates = new Coordinates(8, 9);
        Coordinates expectedCoordinates = new Coordinates(8, 42);
        assertNotEquals("two coordinates are equal!", expectedCoordinates,
                actualCoordinates);
    }

    @Test
    public void testInitPlateauCommandType() {
        Command command = CommandProcessor.interpret("5 5");
        assertTrue(command instanceof InitPlateau);
        assertFalse(command instanceof DeployRover);
    }

    @Test
    public void testInitPlateauCommand() {
        Command command = CommandProcessor.interpret("9 7");
        Coordinates coordinates = ((InitPlateau) command).getCoordinates();
        assertEquals("coordinates are not correct.", new Coordinates(9, 7),
                coordinates);
    }

    @Test
    public void testDeployRoverCommandType() {
        Command command = CommandProcessor.interpret("1 2 N");
        assertTrue(command instanceof DeployRover);
        assertFalse(command instanceof MoveRover);
    }

    @Test
    public void testDeployRoverCommand() {
        Command command = CommandProcessor.interpret("3 5 W");
        Position position = ((DeployRover)command).getPosition();
        assertEquals("position is not correct",
                new Position(3, 5, CardinalDirection.W), position);
    }

    @Test
    public void testMoveRoverCommandType() {
        Command command = CommandProcessor.interpret("RMMRMLLLM");
        assertTrue(command instanceof MoveRover);
        assertFalse(command instanceof InitPlateau);
    }

    @Test
    public void testMoveRoverCommand() {
        Command command = CommandProcessor.interpret("RMML");
        List<Direction> directionList = ((MoveRover) command).getDirections();
        assertEquals("two directions are different", Direction.R, directionList
                .get(0));
        assertEquals("two directions are different", Direction.M, directionList
                .get(1));
        assertEquals("two directions are different", Direction.M, directionList
                .get(2));
        assertEquals("two directions are different", Direction.L, directionList
                .get(3));
    }

    @Test
    public void testLanderDeployRoverCommand() {
        Lander lander = new Lander();
        lander.sendCommand("5 5");
        lander.sendCommand("3 5 W");
        Position roverDeploymentPosition = lander.getRover().getPosition();
        Position expectedPosition = new Position(3, 5, CardinalDirection.W);
        assertEquals("rover is not deployed correctly", expectedPosition,
                roverDeploymentPosition);
    }

    @Test
    public void testLanderInitPlateauCommand() {
        Lander lander = new Lander();
        lander.sendCommand("7 9");
        Plateau initializedPlateau = lander.getPlateau();
        Plateau expectedPlateau = new Plateau(7, 9);
        assertEquals("plateau is not initialized correctly", expectedPlateau,
                initializedPlateau);
    }

    @Test
    public void testLanderMoveRoverCommand() {
        Lander lander = new Lander();
        lander.sendCommand("5 5");
        lander.sendCommand("3 3 E");
        lander.sendCommand("MMRMMRMRRM");
        assertEquals("Rover is in wrong position",
                new Position(5, 1, CardinalDirection.E),
                lander.getRover().getPosition());
    }

    @Test
    public void testLanderMoveAnotherRoverCommand() {
        Lander lander = new Lander();
        lander.sendCommand("5 5");
        lander.sendCommand("1 2 N");
        lander.sendCommand("LMLMLMLMM");
        assertEquals("Rover is in wrong position",
                new Position(1, 3, CardinalDirection.N),
                lander.getRover().getPosition());
    }

}
