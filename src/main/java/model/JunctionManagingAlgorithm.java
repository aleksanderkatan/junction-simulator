package model;

import model.Car;
import model.Direction;

import java.util.List;

public interface JunctionManagingAlgorithm {
    boolean isEmpty();

    List<Car> step();

    void addVehicle(String vehicleId, Direction startRoad, Direction endRoad);
}
