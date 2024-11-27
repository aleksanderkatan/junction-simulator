package model.intensityOptimized;

import model.*;

import java.util.List;

public class IntensityOptimizedAlgorithm implements JunctionManagingAlgorithm {

    private final JunctionRoadLightsStateWrapper roadLights;
    private final JunctionCarsState cars;

    public IntensityOptimizedAlgorithm(JunctionRoadLightsStateWrapper roadLights, JunctionCarsState cars) {
        this.roadLights = roadLights;
        this.cars = cars;
    }

    @Override
    public boolean isEmpty() {
        return cars.isEmpty();
    }

    @Override
    public List<Car> step() {
        var carsDriving = cars.step(roadLights.getRoadLights());

        if (roadLights.needsEnqueueing()) {
            calculateAndEnqueueLights();
        }
        roadLights.step();

        return carsDriving;
    }

    @Override
    public void addVehicle(String vehicleId, Direction startRoad, Direction endRoad) {
        cars.addCar(new Car(vehicleId, startRoad, endRoad));
    }

    public void calculateAndEnqueueLights() {
        // currently, NORTH and SOUTH are green.
        // find values for how long NORTH should stay green, and how long should EAST stay green after that.
        int northCars = cars.northSouthCarsCount();
        int eastCars = cars.eastWestCarsCount();

        // the (2+) segment incorporates the time required for switching lights
        int northTime = northCars == 0 ? 0 : 2+Math.min(Math.max(3, northCars / 3), 20);
        int eastTime = eastCars == 0 ? 0 : 2+Math.min(Math.max(3, eastCars / 3), 20);

        System.out.printf("North/South cars waiting:%d, East/West cars waiting: %d. " +
                "Queueing North/South lights for %d steps, then East/West lights for %d steps.%n",
                northCars, eastCars, northTime, eastTime);

        roadLights.enqueue(northTime, eastTime);
    }
}
