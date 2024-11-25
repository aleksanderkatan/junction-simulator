package controller;

import model.Direction;
import model.JunctionManager;
import org.apache.commons.cli.*;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("The application started running.");
        Options options = new Options();
        options.addOption("h", "head", false, "Enable junction visualization");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            boolean isHeaded = cmd.hasOption("h");
            String[] remainingArgs = cmd.getArgs();
            if (remainingArgs.length != 2) {
                throw new IllegalArgumentException("Exactly 2 arguments required");
            }
            System.out.printf("Reading input from %s, the output will be stored in %s%n", remainingArgs[0], remainingArgs[1]);

        } catch (ParseException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
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