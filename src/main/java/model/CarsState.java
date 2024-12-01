package model;

import java.util.*;

import static model.Direction.*;

public class CarsState {
    private final Map<Direction, Queue<Car>> carsWaiting;

    public CarsState() {
        carsWaiting = new HashMap<>();
        for (var direction : Direction.values()) {
            carsWaiting.put(direction, new LinkedList<>());
        }
    }

    public void addCar(Car car) {
        carsWaiting.get(car.start()).add(car);
    }

    public List<Car> step(Map<Direction, RoadLightColor> roadLights) {
        // This method assumes that:
        // - it is impossible for two cars on perpendicular roads to enter simultaneously,
        // - only one car can drive from a lane in a singular step.
        List<Car> carsThatDrive = findCarsThatDrive(roadLights);
        for (var queue : carsWaiting.values()) {
            if (carsThatDrive.contains(queue.peek())) {
                queue.remove();
            }
        }
        return carsThatDrive;
    }

    public int northSouthCarsCount() {
        return carsWaiting.get(NORTH).size() + carsWaiting.get(SOUTH).size();
    }

    public int eastWestCarsCount() {
        return carsWaiting.get(EAST).size() + carsWaiting.get(WEST).size();
    }

    public boolean isEmpty() {
        return northSouthCarsCount() + eastWestCarsCount() == 0;
    }

    private List<Car> findCarsThatDrive(Map<Direction, RoadLightColor> roadLights) {
        List<Car> carsThatDrive = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            if (!roadLights.get(direction).allowsDriving()) {
                continue;
            }
            var currentQueue = carsWaiting.get(direction);
            if (currentQueue.isEmpty()) {
                continue;
            }

            var car = currentQueue.peek();

            // By assumption, driving forward or right has no potential collision.
            if (car.goesRight() || car.goesForward()) {
                carsThatDrive.add(car);
                continue;
            }

            // If there is no car on the opposite road, there is no potential collision.
            var oppositeQueue = carsWaiting.get(Direction.forwardTo(car.start()));
            if (oppositeQueue.isEmpty()) {
                carsThatDrive.add(car);
                continue;
            }

            // If the car wants to drive left, it can as long as the opposite car is not driving forward or right.
            // NOTE: the car does not know whether the opposite road has red light.
            // With multi-lane setup it could be possible to let it know that it can drive.
            var oppositeCar = oppositeQueue.peek();
            if (car.goesLeft() && (oppositeCar.goesLeft() || oppositeCar.turnsBack())) {
                carsThatDrive.add(car);
            }

            // If the car wants to turn back, it can as long as the opposite car is not driving forward.
            if (car.turnsBack() && !oppositeCar.goesForward()) {
                carsThatDrive.add(car);
            }
            // In other cases, the car waits.
        }

        return carsThatDrive;
    }
}
