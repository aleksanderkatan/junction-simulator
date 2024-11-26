package model;

import java.util.*;

public abstract class JunctionCarsState {
    private final Map<Direction, Queue<Car>> carsWaiting;

    public JunctionCarsState() {
        carsWaiting = new HashMap<>();
        for (var direction : Direction.values()) {
            carsWaiting.put(direction, new LinkedList<>());
        }
    }

    public void addCar(Car car) {
        carsWaiting.get(car.start()).add(car);
    }

    public List<Car> stepCars(JunctionRoadLightsState roadLights) {
        // TODO: implement
        throw new RuntimeException("Method not yet implemented");
    }
}
