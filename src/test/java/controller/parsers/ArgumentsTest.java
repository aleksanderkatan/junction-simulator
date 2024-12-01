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
    void defaultsToCorrectOptions() {
        String[] args = {"input.txt", "output.txt"};

        Arguments arguments = Arguments.parseFromStringArr(args);

        assertFalse(arguments.isHeaded());
        assertFalse(arguments.isCyclic());
        assertTrue(arguments.isFlushing());
    }

    @Test
    void parsesOptions() {
        String[] args = {"-h", "input.txt", "-c", "output.txt", "-f"};

        Arguments arguments = Arguments.parseFromStringArr(args);

        assertTrue(arguments.isHeaded());
        assertTrue(arguments.isCyclic());
        assertFalse(arguments.isFlushing());
    }
}