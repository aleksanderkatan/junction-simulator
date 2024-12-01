package model.cyclic;

import model.Car;
import model.CarsState;
import model.JunctionManagingAlgorithm;
import model.RoadLights;

import java.util.List;

public class CyclicAlgorithm implements JunctionManagingAlgorithm {
    private final RoadLights roadLights;
    private final CarsState cars;
    private final CyclicCounter cyclicCounter;

    public CyclicAlgorithm(RoadLights roadLights, CarsState cars, CyclicCounter cyclicCounter) {
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
    public void addVehicle(Car car) {
        cars.addCar(car);
    }
}
