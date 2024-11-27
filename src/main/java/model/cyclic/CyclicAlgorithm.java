package model.cyclic;

import model.*;

import java.util.List;

public class CyclicAlgorithm implements JunctionManagingAlgorithm {
    private final JunctionRoadLightsState roadLights;
    private final JunctionCarsState cars;
    private final CyclicCounter cyclicCounter;

    public CyclicAlgorithm(JunctionRoadLightsState roadLights, JunctionCarsState cars, CyclicCounter cyclicCounter) {
        this.roadLights = roadLights;
        this.cars = cars;
        this.cyclicCounter = cyclicCounter;
    }

    @Override
    public boolean isEmpty() {
        return cars.isEmpty();
    }

    @Override
    public List<Car> step() {
        var carsDriving = cars.step(roadLights.getRoadLights());
        roadLights.step();

        if (cyclicCounter.step()) {
            roadLights.switchLights();
        }

        return carsDriving;
    }

    @Override
    public void addVehicle(String vehicleId, Direction startRoad, Direction endRoad) {
        cars.addCar(new Car(vehicleId, startRoad, endRoad));
    }
}
