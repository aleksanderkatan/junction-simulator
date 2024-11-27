package controller.parsers;

import org.apache.commons.cli.*;

import java.nio.file.Path;

public record Arguments(Path inputPath, Path outputPath, boolean isHeaded, boolean isFlushing) {
    public static Arguments parseFromStringArr(String[] args) {
        Options options = new Options();
        options.addOption("h", "head", false, "Enable junction visualization (currently unsupported)");
        options.addOption("f", "flush", true, "Continue the simulation until all cars leave");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            boolean isHeaded = cmd.hasOption("h");
            boolean isFlushing = cmd.hasOption("f");
            String[] remainingArgs = cmd.getArgs();
            if (remainingArgs.length != 2) {
                throw new IllegalArgumentException("Exactly 2 arguments required");
            }
            return new Arguments(Path.of(remainingArgs[0]), Path.of(remainingArgs[1]), isHeaded, isFlushing);

        } catch (ParseException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
