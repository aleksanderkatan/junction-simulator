package model;

import model.cyclic.CyclicCounter;
import model.cyclic.CyclicAlgorithm;
import model.intensityOptimized.IntensityOptimizedAlgorithm;
import model.intensityOptimized.JunctionRoadLightsStateWrapper;

public class JunctionManagingAlgorithmFactory {
    public static CyclicAlgorithm produceCyclic() {
        JunctionRoadLightsState roadLights = new JunctionRoadLightsState();
        JunctionCarsState cars = new JunctionCarsState();
        CyclicCounter cyclicCounter = new CyclicCounter(5);
        return new CyclicAlgorithm(roadLights, cars, cyclicCounter);
    }

    public static IntensityOptimizedAlgorithm produceIntensityOptimized() {
        JunctionRoadLightsState roadLights = new JunctionRoadLightsState();
        JunctionRoadLightsStateWrapper wrapper = new JunctionRoadLightsStateWrapper(roadLights);
        JunctionCarsState cars = new JunctionCarsState();
        return new IntensityOptimizedAlgorithm(wrapper, cars);
    }
}
