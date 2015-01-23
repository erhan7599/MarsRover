package com.infonal;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.infonal.CardinalDirection;
import com.infonal.Lander;
import com.infonal.Plateau;
import com.infonal.Position;
import com.infonal.Rover;

import static org.junit.Assert.assertEquals;

public class LandRoverToMarsSteps {
    private Lander lander;
    private Plateau plateau;

    @Given("a lander")
    public void setLander() {
        lander = new Lander();
    }

    @Given("a plateau with dimensions $x, $y")
    public void setPlateau(int x, int y) {
        plateau = new Plateau(x, y);
    }

    @Given("a rover at position <position_x>, <position_y>, <heading>")
    public void setRover(@Named("position_x") int x,
                         @Named("position_y") int y,
                         @Named("heading") String heading) {
        CardinalDirection direction = CardinalDirection.valueOf(heading);
        Rover rover = new Rover(x, y, direction);
        rover.setPlateau(plateau);
        lander.setRover(rover);
    }

    @When("lander is commanded as <command>")
    public void commandSend(@Named("command") String command) {
        lander.sendCommand(command);
    }

    @Then("rover should end up at <end_position_x>, <end_position_y>, <end_heading>")
    public void positionShouldBe(@Named("end_position_x") int x,
                                 @Named("end_position_y") int y,
                                 @Named("end_heading") String direction) {
        CardinalDirection cardinalDirection = CardinalDirection
                .valueOf(direction);
        Position position = new Position(x, y, cardinalDirection);
        assertEquals(position, lander.getRover().getPosition());
    }
}
