package model;

import org.junit.jupiter.api.Test;

import static model.Direction.EAST;
import static model.Direction.NORTH;
import static model.RoadLightColor.*;
import static org.junit.jupiter.api.Assertions.*;

class JunctionRoadLightsTest {
    @Test
    void stepDoesNothingWhenNothingEnqueued() {
        var state = new JunctionRoadLights();
        var lights = state.getRoadLights();

        state.step();
        var newLights = state.getRoadLights();

        assertEquals(lights, newLights);
    }

    @Test
    void northRotatesCorrectly() {
        var state = new JunctionRoadLights();

        var color0 = state.getRoadLights().get(NORTH);
        state.switchLights();
        state.step();
        var color1 = state.getRoadLights().get(NORTH);
        state.step();
        var color2 = state.getRoadLights().get(NORTH);
        state.step();
        var color3 = state.getRoadLights().get(NORTH);
        state.switchLights();
        state.step();
        var color4 = state.getRoadLights().get(NORTH);
        state.step();
        var color5 = state.getRoadLights().get(NORTH);
        state.step();
        var color6 = state.getRoadLights().get(NORTH);

        assertEquals(GREEN, color0);
        assertEquals(YELLOW, color1);
        assertEquals(RED, color2);
        assertEquals(RED, color3);
        assertEquals(RED, color4);
        assertEquals(RED_YELLOW, color5);
        assertEquals(GREEN, color6);
    }

    @Test
    void eastRotatesCorrectly() {
        var state = new JunctionRoadLights();

        var color0 = state.getRoadLights().get(EAST);
        state.switchLights(EAST);
        state.step();
        var color1 = state.getRoadLights().get(EAST);
        state.step();
        var color2 = state.getRoadLights().get(EAST);
        state.step();
        var color3 = state.getRoadLights().get(EAST);
        state.switchLights(NORTH);
        state.step();
        var color4 = state.getRoadLights().get(EAST);
        state.step();
        var color5 = state.getRoadLights().get(EAST);
        state.step();
        var color6 = state.getRoadLights().get(EAST);

        assertEquals(RED, color0);
        assertEquals(RED, color1);
        assertEquals(RED_YELLOW, color2);
        assertEquals(GREEN, color3);
        assertEquals(YELLOW, color4);
        assertEquals(RED, color5);
        assertEquals(RED, color6);
    }

}