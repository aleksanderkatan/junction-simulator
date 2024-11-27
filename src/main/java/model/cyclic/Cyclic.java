package model.cyclic;

import model.*;

import java.util.List;

public class Cyclic implements JunctionManagingAlgorithm {
    private final JunctionRoadLightsState roadLights;
    private final JunctionCarsState cars;
    private final Counter counter;

    public Cyclic(JunctionRoadLightsState roadLights, JunctionCarsState cars, Counter counter) {
        this.roadLights = roadLights;
        this.cars = cars;
        this.counter = counter;
    }

    @Override
    public boolean isEmpty() {
        return cars.isEmpty();
    }

    @Override
    public List<Car> step() {
        var carsDriving = cars.step(roadLights.getRoadLights());

        if (counter.step()) {
            roadLights.switchLights();
        }

        return carsDriving;
    }

    @Override
    public void addVehicle(String vehicleId, Direction startRoad, Direction endRoad) {
        cars.addCar(new Car(vehicleId, startRoad, endRoad));
    }
}
