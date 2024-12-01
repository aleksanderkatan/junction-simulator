package controller.parsers;

import model.Car;
import model.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultStringifierTest {
    private String removeWhitespaces(String s) {
        return s.replaceAll("\\s+","");
    }

    @Test
    void parsesEmptyResult() {
        List<List<Car>> list = new ArrayList<>();

        String result = ResultStringifier.toString(list);

        assertEquals("{\"stepStatuses\":[]}", removeWhitespaces(result));
    }

    @Test
    void parsesTwoCars() {
        List<List<Car>> list = List.of(List.of(
                new Car("id1", Direction.NORTH, Direction.NORTH),
                new Car("id2", Direction.NORTH, Direction.NORTH)
        ));

        String result = ResultStringifier.toString(list);

        assertEquals("{\"stepStatuses\":[{\"leftVehicles\":[\"id1\",\"id2\"]}]}", removeWhitespaces(result));
    }

}