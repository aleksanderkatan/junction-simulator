package controller;

import controller.arguments.ArgumentsParser;
import model.Direction;
import model.JunctionManager;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("The application started running.");

        ArgumentsParser argumentsParser = ArgumentsParser.parseFromStringArr(args);
        System.out.printf("Reading input from %s, the output will be stored in %s%n",
                argumentsParser.inputPath().toString(), argumentsParser.outputPath());



    }

    private static void mainLoop(List<Map<String, String>> events, JunctionManager junctionManager) {
        for (var event: events) {
            switch (event.get("type")) {
                case "addVehicle" -> junctionManager.addVehicle(
                        event.get("vehicleId"),
                        Direction.fromString(event.get("startRoad")),
                        Direction.fromString(event.get("endRoad"))
                );
                case "step" -> junctionManager.step();
                default -> System.out.println("Unknown event type");
            }
        }

    }
}