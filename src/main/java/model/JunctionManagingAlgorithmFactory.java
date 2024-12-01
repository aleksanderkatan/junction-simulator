package model;

import model.cyclic.CyclicAlgorithm;
import model.cyclic.CyclicCounter;
import model.intensityOptimized.DoubleIterationCounter;
import model.intensityOptimized.IntensityOptimizedAlgorithm;
import model.intensityOptimized.JunctionRoadLightsStateWrapper;

public class JunctionManagingAlgorithmFactory {
    private static CyclicAlgorithm produceCyclic() {
        var roadLights = new RoadLights();
        var cars = new CarsState();
        var cyclicCounter = new CyclicCounter(5);
        return new CyclicAlgorithm(roadLights, cars, cyclicCounter);
    }

    private static IntensityOptimizedAlgorithm produceIntensityOptimized() {
        var roadLights = new RoadLights();
        var counter = new DoubleIterationCounter(0, 0);
        var wrapper = new JunctionRoadLightsStateWrapper(roadLights, counter);
        var cars = new CarsState();
        return new IntensityOptimizedAlgorithm(wrapper, cars);
    }

    public static JunctionManagingAlgorithm produce(boolean cyclic) {
        if (cyclic) {
            return produceCyclic();
        } else {
            return produceIntensityOptimized();
        }
    }
}
