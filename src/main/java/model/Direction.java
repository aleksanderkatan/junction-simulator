package model;

import java.util.List;

public enum Direction {
    EAST, NORTH, WEST, SOUTH;

    private static final List<Direction> counterClockwiseOrder = List.of(EAST, NORTH, WEST, SOUTH);

    public static Direction rightTo(Direction direction) {
        return cyclicNext(direction, 1);
    }

    public static Direction forwardTo(Direction direction) {
        return cyclicNext(direction, 2);
    }

    public static Direction leftTo(Direction direction) {
        return cyclicNext(direction, 3);
    }

    public static Direction fromString(String s) {
        return Direction.valueOf(s.strip().toUpperCase());
    }

    private static Direction cyclicNext(Direction direction, int i) {
        int index = counterClockwiseOrder.indexOf(direction);
        int nextIndex = (index + i) % 4;
        return counterClockwiseOrder.get(nextIndex);
    }
}
