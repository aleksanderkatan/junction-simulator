package model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static model.Direction.*;
import static model.RoadLightColor.*;
import static org.junit.jupiter.api.Assertions.*;

class CarsStateTest {

    @Test
    void carDrivesWhenGreen() {
        var car1 = new Car("car1", NORTH, EAST);
        var carsState = new CarsState();
        carsState.addCar(car1);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, GREEN,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(car1), carsThatDrive);
    }

    @Test
    void carIsRemovedFromQueueAfterDriving() {
        var car1 = new Car("car1", NORTH, SOUTH);
        var carsState = new CarsState();
        carsState.addCar(car1);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, RED,
                WEST, RED
        );

        carsState.step(lights);

        assertTrue(carsState.isEmpty());
    }

    @Test
    void carStaysWhenRed() {
        var car1 = new Car("car1", NORTH, SOUTH);
        var carsState = new CarsState();
        carsState.addCar(car1);
        var lights = Map.of(
                NORTH, RED,
                EAST, RED,
                SOUTH, RED,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(), carsThatDrive);
    }

    @Test
    void carsDriveForward() {
        var car1 = new Car("car1", NORTH, SOUTH);
        var car2 = new Car("car2", SOUTH, NORTH);
        var carsState = new CarsState();
        carsState.addCar(car1);
        carsState.addCar(car2);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, GREEN,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(car1, car2), carsThatDrive);
    }

    @Test
    void carsDriveLeft() {
        var car1 = new Car("car1", NORTH, EAST);
        var car2 = new Car("car2", SOUTH, WEST);
        var carsState = new CarsState();
        carsState.addCar(car1);
        carsState.addCar(car2);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, GREEN,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(car1, car2), carsThatDrive);
    }

    @Test
    void carsDriveRight() {
        var car1 = new Car("car1", NORTH, WEST);
        var car2 = new Car("car2", SOUTH, EAST);
        var carsState = new CarsState();
        carsState.addCar(car1);
        carsState.addCar(car2);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, GREEN,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(car1, car2), carsThatDrive);
    }

    @Test
    void carsTurnBack() {
        var car1 = new Car("car1", NORTH, NORTH);
        var car2 = new Car("car2", SOUTH, SOUTH);
        var carsState = new CarsState();
        carsState.addCar(car1);
        carsState.addCar(car2);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, GREEN,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(car1, car2), carsThatDrive);
    }

    @Test
    void carStaysWhenNoPrecedence() {
        var car1 = new Car("car1", NORTH, SOUTH);
        var car2 = new Car("car2", SOUTH, WEST);
        var carsState = new CarsState();
        carsState.addCar(car1);
        carsState.addCar(car2);
        var lights = Map.of(
                NORTH, GREEN,
                EAST, RED,
                SOUTH, GREEN,
                WEST, RED
        );

        var carsThatDrive = carsState.step(lights);

        assertEquals(List.of(car1), carsThatDrive);
    }

    @Test
    void isEmptyWhenEmptyReturnsTrue() {
        var carsState = new CarsState();

        var isEmpty = carsState.isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    void isEmptyWhenNotEmptyReturnsFalse() {
        var car1 = new Car("car1", NORTH, SOUTH);
        var carsState = new CarsState();
        carsState.addCar(car1);

        var isEmpty = carsState.isEmpty();

        assertFalse(isEmpty);
    }

}