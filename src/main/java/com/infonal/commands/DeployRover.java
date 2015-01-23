package com.infonal.commands;

import com.infonal.Position;


 //Command to deploy rover to the Mars ground sent by NASA to lander.
 
public class DeployRover extends Command {
    private Position position;

    public DeployRover(Position pPosition) {
        position = pPosition;
    }

    public Position getPosition() {
        return position;
    }
}
