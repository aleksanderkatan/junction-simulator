package model.cyclic;

public class CyclicCounter {
    private final int cycleLength;
    private int timeRemaining;
    public CyclicCounter(int cycleLength) {
        this.cycleLength = cycleLength;
        timeRemaining = cycleLength;
    }
    boolean step() {
        timeRemaining -= 1;
        if (timeRemaining <= 0) {
            timeRemaining = cycleLength;
            return true;
        }
        return false;
    }
}