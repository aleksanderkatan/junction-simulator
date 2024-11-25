package controller;

import org.apache.commons.cli.*;

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
}