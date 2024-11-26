package controller.parsers;

import org.apache.commons.cli.*;

import java.nio.file.Path;

public record ArgumentsParser(Path inputPath, Path outputPath, boolean head) {
    public static ArgumentsParser parseFromStringArr(String[] args) {
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
            return new ArgumentsParser(Path.of(remainingArgs[0]), Path.of(remainingArgs[1]), isHeaded);

        } catch (ParseException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
