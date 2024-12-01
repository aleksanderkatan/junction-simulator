package model.cyclic;

import model.Direction;
import model.JunctionCarsState;
import model.JunctionRoadLights;
import model.RoadLightColor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CyclicAlgorithmTest {
    @Test
    void callsSteps() {
        var roadLights = mock(JunctionRoadLights.class);
        var mapToReturn = new HashMap<Direction, RoadLightColor>();
        when(roadLights.getRoadLights()).thenReturn(mapToReturn);
        var carsState = mock(JunctionCarsState.class);
        var counter = mock(CyclicCounter.class);
        var algorithm = new CyclicAlgorithm(roadLights, carsState, counter);

        algorithm.step();

        verify(roadLights).step();
        verify(carsState).step(mapToReturn);
        verify(counter).step();
    }

    @Test
    void switchesWhenCounterTicks() {
        var roadLights = mock(JunctionRoadLights.class);
        var carsState = mock(JunctionCarsState.class);
        var counter = mock(CyclicCounter.class);
        when(counter.step()).thenReturn(true);
        var algorithm = new CyclicAlgorithm(roadLights, carsState, counter);

        algorithm.step();

        verify(roadLights).switchLights();
    }

    @Test
    void doesNotSwitchWhenCounterDoesNotTick() {
        var roadLights = mock(JunctionRoadLights.class);
        var carsState = mock(JunctionCarsState.class);
        var counter = mock(CyclicCounter.class);
        when(counter.step()).thenReturn(false);
        var algorithm = new CyclicAlgorithm(roadLights, carsState, counter);

        algorithm.step();

        verify(roadLights, never()).switchLights();
    }

}