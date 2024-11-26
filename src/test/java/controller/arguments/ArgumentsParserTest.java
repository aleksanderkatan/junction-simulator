package controller.arguments;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentsParserTest {
    @Test
    void throwsWhenNoArgumentsGiven() {
        String[] args = {};

        assertThrows(RuntimeException.class, () -> ArgumentsParser.parseFromStringArr(args));
    }

    @Test
    void throwsWhenOneArgumentGiven() {
        String[] args = {"path.txt"};

        assertThrows(RuntimeException.class, () -> ArgumentsParser.parseFromStringArr(args));
    }

    @Test
    void parsesTwoArguments() {
        String[] args = {"input.txt", "output.txt"};

        ArgumentsParser argumentsParser = ArgumentsParser.parseFromStringArr(args);

        assertEquals(Path.of("input.txt"), argumentsParser.inputPath());
        assertEquals(Path.of("output.txt"), argumentsParser.outputPath());
    }

    @Test
    void parsesHeadOption() {
        String[] args = {"-h", "input.txt", "output.txt"};

        ArgumentsParser argumentsParser = ArgumentsParser.parseFromStringArr(args);

        assertEquals(Path.of("input.txt"), argumentsParser.inputPath());
        assertEquals(Path.of("output.txt"), argumentsParser.outputPath());
        assertTrue(argumentsParser.head());
    }
}