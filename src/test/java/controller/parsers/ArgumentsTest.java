package controller.parsers;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentsTest {
    @Test
    void throwsWhenNoArgumentsGiven() {
        String[] args = {};

        assertThrows(RuntimeException.class, () -> Arguments.parseFromStringArr(args));
    }

    @Test
    void throwsWhenOneArgumentGiven() {
        String[] args = {"path.txt"};

        assertThrows(RuntimeException.class, () -> Arguments.parseFromStringArr(args));
    }

    @Test
    void parsesTwoArguments() {
        String[] args = {"input.txt", "output.txt"};

        Arguments arguments = Arguments.parseFromStringArr(args);

        assertEquals(Path.of("input.txt"), arguments.inputPath());
        assertEquals(Path.of("output.txt"), arguments.outputPath());
    }

    @Test
    void parsesHeadOption() {
        String[] args = {"-h", "input.txt", "output.txt"};

        Arguments arguments = Arguments.parseFromStringArr(args);

        assertEquals(Path.of("input.txt"), arguments.inputPath());
        assertEquals(Path.of("output.txt"), arguments.outputPath());
        assertTrue(arguments.isHeaded());
    }
}