package model;

import model.cyclic.CyclicCounter;
import model.cyclic.CyclicAlgorithm;
import model.intensityOptimized.DoubleIterationCounter;
import model.intensityOptimized.IntensityOptimizedAlgorithm;
import model.intensityOptimized.JunctionRoadLightsStateWrapper;

public class JunctionManagingAlgorithmFactory {
    public static CyclicAlgorithm produceCyclic() {
        var roadLights = new JunctionRoadLights();
        var cars = new JunctionCarsState();
        var cyclicCounter = new CyclicCounter(5);
        return new CyclicAlgorithm(roadLights, cars, cyclicCounter);
    }

    public static IntensityOptimizedAlgorithm produceIntensityOptimized() {
        var roadLights = new JunctionRoadLights();
        var counter = new DoubleIterationCounter(0, 0);
        var wrapper = new JunctionRoadLightsStateWrapper(roadLights, counter);
        var cars = new JunctionCarsState();
        return new IntensityOptimizedAlgorithm(wrapper, cars);
    }
}
