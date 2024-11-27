package model;

import model.cyclic.Counter;
import model.cyclic.Cyclic;

public class JunctionManagingAlgorithmFactory {
    public static Cyclic produceCyclic() {
        JunctionRoadLightsState roadLights = new JunctionRoadLightsState();
        JunctionCarsState cars = new JunctionCarsState();
        Counter counter = new Counter(5);
        return new Cyclic(roadLights, cars, counter);
    }
}
