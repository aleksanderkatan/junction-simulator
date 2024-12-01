package model.intensityOptimized;

import model.Direction;
import model.CarsState;
import model.RoadLightColor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.Mockito.*;

class IntensityOptimizedAlgorithmTest {
    // the expected function is (cars == 0 ? 0 : 2+min(max(3, cars / 3), 20))

    @Test
    void whenNoCarsOnEWThenEnqueuesNothing() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        when(wrapper.needsEnqueueing()).thenReturn(true);
        var cars = mock(CarsState.class);
        when(cars.northSouthCarsCount()).thenReturn(3);
        when(cars.eastWestCarsCount()).thenReturn(0);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(wrapper).enqueue(5, 0);
    }

    @Test
    void whenNoCarsOnSTThenEnqueuesNothing() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        when(wrapper.needsEnqueueing()).thenReturn(true);
        var cars = mock(CarsState.class);
        when(cars.northSouthCarsCount()).thenReturn(0);
        when(cars.eastWestCarsCount()).thenReturn(3);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(wrapper).enqueue(0, 5);
    }

    @Test
    void enqueuesAtMost22Steps() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        when(wrapper.needsEnqueueing()).thenReturn(true);
        var cars = mock(CarsState.class);
        when(cars.northSouthCarsCount()).thenReturn(100);
        when(cars.eastWestCarsCount()).thenReturn(1000000);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(wrapper).enqueue(22, 22);
    }

    @Test
    void enqueuesAtLeast5Steps() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        when(wrapper.needsEnqueueing()).thenReturn(true);
        var cars = mock(CarsState.class);
        when(cars.northSouthCarsCount()).thenReturn(1);
        when(cars.eastWestCarsCount()).thenReturn(2);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(wrapper).enqueue(5, 5);
    }

    @Test
    void enqueuesForProportionallyManySteps() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        when(wrapper.needsEnqueueing()).thenReturn(true);
        var cars = mock(CarsState.class);
        when(cars.northSouthCarsCount()).thenReturn(20);
        when(cars.eastWestCarsCount()).thenReturn(40);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(wrapper).enqueue(2+20/3, 2+40/3);
    }

    @Test
    void doesNotEnqueueWhenNotNeeded() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        when(wrapper.needsEnqueueing()).thenReturn(false);
        var cars = mock(CarsState.class);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(wrapper, never()).enqueue(anyInt(), anyInt());
    }

    @Test
    void callsSteps() {
        var wrapper = mock(JunctionRoadLightsStateWrapper.class);
        var mapToReturn = new HashMap<Direction, RoadLightColor>();
        when(wrapper.getRoadLights()).thenReturn(mapToReturn);
        var cars = mock(CarsState.class);
        var algorithm = new IntensityOptimizedAlgorithm(wrapper, cars);

        algorithm.step();

        verify(cars).step(mapToReturn);
        verify(wrapper).step();
    }
}