package controller.parsers;

import org.apache.commons.cli.*;

import java.nio.file.Path;

public record Arguments(Path inputPath, Path outputPath, boolean isHeaded, boolean isFlushing, boolean isCyclic) {
    public static Arguments parseFromStringArr(String[] args) {
        Options options = new Options();
        options.addOption("h", "head", false,
                "Enable junction visualization (currently unsupported).");
        options.addOption("f", "no-flush", false,
                "Halt the simulation after processing events even if there are cars left.");
        options.addOption("c", "cyclic", false,
                "Use cyclic algorithm instead of the default intensity optimized one.");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            boolean isHeaded = cmd.hasOption("h");
            boolean isFlushing = !cmd.hasOption("f");
            boolean isCyclic = cmd.hasOption("c");
            String[] remainingArgs = cmd.getArgs();
            if (remainingArgs.length != 2) {
                throw new IllegalArgumentException("Exactly 2 arguments required");
            }
            return new Arguments(Path.of(remainingArgs[0]), Path.of(remainingArgs[1]), isHeaded, isFlushing, isCyclic);

        } catch (ParseException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
