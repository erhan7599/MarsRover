package com.infonal.commands;

import java.util.ArrayList;
import java.util.List;

import com.infonal.Direction;


  //Command to move rover.

public class MoveRover extends Command {
    private List<Direction> directions;

    public MoveRover() {
        directions = new ArrayList<Direction>();
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void addDirection(Direction pDirection) {
        directions.add(pDirection);
    }

}
