package com.infonal;

import java.util.List;

import com.infonal.commands.Command;
import com.infonal.commands.DeployRover;
import com.infonal.commands.InitPlateau;
import com.infonal.commands.MoveRover;

/**
 * Class which is responsible for landing rovers to Mars ground, setting their
 * initial data, acting as a relay between NASA and rovers.
 */
public class Lander {
    private Rover rover;

    private Plateau plateau;

    private CommandProcessor commandProcessor;

    public Lander() {

    }

    public Rover getRover() {

       return rover;
    }

    public void setRover(Rover pRover) {
        rover = pRover;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Method which interprets NASA's commands using an interpreter and
     * sends translated commands to rover.
     * @param message
     * @return
     */
    public String sendCommand(String message) {
        Command command = commandProcessor.interpret(message);
        String response = null;
        if (command instanceof DeployRover) {
            Position position = ((DeployRover) command).getPosition();
            rover = new Rover(position);
            rover.setPlateau(plateau);
            response = command.getSuccess();
        } else if (command instanceof MoveRover) {
            if (rover != null) {
                List<Direction> directionList = ((MoveRover) command)
                        .getDirections();
                for (Direction direction : directionList) {
                    rover.sendCommand(direction);
                }
                response = command.getSuccess();
            } else {
                response = "First deploy a rover to Mars!";
            }
        } else if (command instanceof InitPlateau) {
            Coordinates coordinates = ((InitPlateau) command)
                    .getCoordinates();
            plateau = new Plateau(coordinates);
            response = command.getSuccess();
        }
        return response;
    }
}
