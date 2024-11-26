package model.algorithm;

import model.Car;
import model.Direction;

import java.util.List;

public interface JunctionManagingAlgorithm {
    List<Car> step();

    void addVehicle(String vehicleId, Direction startRoad, Direction endRoad);
}
