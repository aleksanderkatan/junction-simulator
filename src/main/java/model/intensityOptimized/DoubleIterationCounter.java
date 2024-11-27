package model.intensityOptimized;

import model.Direction;

import java.util.Optional;

public class DoubleIterationCounter {
    private int length1;
    private int length2;

    public DoubleIterationCounter(int length1, int length2) {
        this.length1 = length1;
        this.length2 = length2;
    }

    public Optional<Direction> step() {
        if (length1 > 0) {
            length1 -= 1;
            return Optional.of(Direction.NORTH);
        }
        if (length2 > 0) {
            length2 -= 1;
            return Optional.of(Direction.EAST);
        }
        return Optional.empty();
    }

    public boolean hasFinished() {
        return length1 <= 0 && length2 <= 0;
    }

    public void refresh(int length1, int length2) {
        this.length1 = length1;
        this.length2 = length2;
    }

}
