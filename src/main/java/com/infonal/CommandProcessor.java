package com.infonal;

import com.infonal.commands.Command;
import com.infonal.commands.DeployRover;
import com.infonal.commands.InitPlateau;
import com.infonal.commands.MoveRover;

public class CommandProcessor {

    public final static String DEPLOY_SUCCESS = "Rover deployed.";

    public final static String DEPLOY_ERROR = "First deploy a rover to Mars!";

    public final static String INIT_PLATEAU_SUCCESS = "Plateau boundaries are set.";

    public final static String MOVE_ROVER_SUCCESS = "Rover is moving to position";

    /**
     * method to interpret String based messages and turn them into a format
     * which Lander can understand.
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends Command> T interpret(String message) {
        T command = null;
        if (Character.isDigit(message.charAt(0))) {
            String commands[] = message.split(" ");
            if (commands.length == 2) {
                int x = Integer.valueOf(commands[0]);
                int y = Integer.valueOf(commands[1]);
                Coordinates coordinates = new Coordinates(x, y);
                InitPlateau initPlateau = new InitPlateau(coordinates);
                initPlateau.setSuccess(DEPLOY_SUCCESS);
                command = (T) initPlateau;
            } else if (commands.length == 3) {
                int x = Integer.valueOf(commands[0]);
                int y = Integer.valueOf(commands[1]);
                Coordinates coordinates = new Coordinates(x, y);
                CardinalDirection direction = CardinalDirection
                        .valueOf(commands[2]);
                Position position = new Position(x, y, direction);
                DeployRover deployRover = new DeployRover(position);
                deployRover.setSuccess(DEPLOY_SUCCESS);
                deployRover.setError(DEPLOY_ERROR);
                command = (T) deployRover;
            }
        } else {
            MoveRover moveRover = new MoveRover();
            moveRover.setSuccess(MOVE_ROVER_SUCCESS);
            moveRover.setError(DEPLOY_ERROR);
            for (int i = 0; i < message.length(); i++) {
                Direction direction = Direction.valueOf(String.valueOf(message
                        .charAt(i)));
                moveRover.addDirection(direction);
            }
            command = (T) moveRover;
        }
        return command;
    }
}
