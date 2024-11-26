package model;

import org.junit.jupiter.api.Test;

import static model.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {
    @Test
    void parsesLowerCaseString() {
        String directionString = "north";

        Direction direction = Direction.fromString(directionString);

        assertEquals(NORTH, direction);
    }

    @Test
    void testRight() {
        assertEquals(EAST, Direction.rightTo(SOUTH));
        assertEquals(NORTH, Direction.rightTo(EAST));
        assertEquals(WEST, Direction.rightTo(NORTH));
        assertEquals(SOUTH, Direction.rightTo(WEST));
    }

    @Test
    void testForward() {
        assertEquals(NORTH, Direction.forwardTo(SOUTH));
        assertEquals(SOUTH, Direction.forwardTo(NORTH));
        assertEquals(EAST, Direction.forwardTo(WEST));
        assertEquals(WEST, Direction.forwardTo(EAST));
    }

    @Test
    void testLeft() {
        assertEquals(WEST, Direction.leftTo(SOUTH));
        assertEquals(SOUTH, Direction.leftTo(EAST));
        assertEquals(EAST, Direction.leftTo(NORTH));
        assertEquals(NORTH, Direction.leftTo(WEST));
    }

}