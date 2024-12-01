package model.intensityOptimized;

import model.JunctionRoadLights;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static model.Direction.NORTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JunctionRoadLightsStateWrapperTest {
    @Test
    void whenCounterReturnsSwitchesLights() {
        var roadLights = mock(JunctionRoadLights.class);
        var counter = mock(DoubleIterationCounter.class);
        when(counter.step()).thenReturn(Optional.of(NORTH));
        var wrapper = new JunctionRoadLightsStateWrapper(roadLights, counter);

        wrapper.step();

        verify(counter).step();
        verify(roadLights).step();
        verify(roadLights).switchLights(NORTH);
    }

    @Test
    void whenCounterReturnsNothingThenDoesNotSwitch() {
        var roadLights = mock(JunctionRoadLights.class);
        var counter = mock(DoubleIterationCounter.class);
        when(counter.step()).thenReturn(Optional.empty());
        var wrapper = new JunctionRoadLightsStateWrapper(roadLights, counter);

        wrapper.step();

        verify(counter).step();
        verify(roadLights).step();
        verifyNoMoreInteractions(roadLights);
    }

}