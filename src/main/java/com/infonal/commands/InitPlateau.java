package com.infonal.commands;

import com.infonal.Coordinates;


  //Command to initialize plateau to be explored.
 
public class InitPlateau extends Command {
    private Coordinates coordinates;

    public InitPlateau(Coordinates pCoordinates) {
        coordinates = pCoordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
