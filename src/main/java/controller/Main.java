package controller;

import controller.parsers.EventParser;
import controller.parsers.ArgumentsParser;
import model.Car;
import model.Direction;
import model.JunctionManagingAlgorithm;
import model.starvePreventing.StarvePreventingAlgorithm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the application.");
        ArgumentsParser argumentsParser = ArgumentsParser.parseFromStringArr(args);

        System.out.printf("Reading input from %s.%n", argumentsParser.inputPath().toString());
        String json = readFile(argumentsParser.inputPath());

        System.out.println("Parsing the json string.");
        var events = EventParser.parseEvents(json);

        System.out.println("Entering the main loop.");
        JunctionManagingAlgorithm algorithm = new StarvePreventingAlgorithm();
        var result = mainLoop(events, algorithm);

        System.out.println("Converting the output to JSON.");
        // TODO: implement

        System.out.printf("Saving the output to %s.%n", argumentsParser.outputPath());
        // TODO: implement
    }

    private static List<List<Car>> mainLoop(List<Map<String, String>> events, JunctionManagingAlgorithm junctionManagingAlgorithm) {
        List<List<Car>> result = new ArrayList<>();
        for (var event: events) {
            switch (event.get("type")) {
                case "addVehicle" -> junctionManagingAlgorithm.addVehicle(
                        event.get("vehicleId"),
                        Direction.fromString(event.get("startRoad")),
                        Direction.fromString(event.get("endRoad"))
                );
                case "step" -> result.add(junctionManagingAlgorithm.step());
                default -> System.out.println("Unknown event type");
            }
        }
        return result;
    }

    private static String readFile(Path inputPath) {
        try {
            return Files.readString(inputPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}