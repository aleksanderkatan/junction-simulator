package model;

import java.util.*;

public class JunctionCarsState {
    private final Map<Direction, Queue<Car>> carsWaiting;

    public JunctionCarsState() {
        carsWaiting = new HashMap<>();
        for (var direction : Direction.values()) {
            carsWaiting.put(direction, new LinkedList<>());
        }
    }

    public Map<Direction, Queue<Car>> getCarsWaiting() {
        return carsWaiting;
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

    private List<Car> findCarsThatDrive(Map<Direction, RoadLightColor> roadLights) {
        List<Car> carsThatDrive = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            if (!roadLights.get(direction).allowsDriving()) {
                continue;
            }            var currentQueue = carsWaiting.get(direction);
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

    public boolean isEmpty() {
        for (var queue : carsWaiting.values()) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
