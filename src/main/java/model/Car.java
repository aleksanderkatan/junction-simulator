package model;

public record Car(String identifier, Direction start, Direction destination) {
    public boolean goesRight() {
        return destination == Direction.rightTo(start);
    }

    public boolean goesForward() {
        return destination == Direction.forwardTo(start);
    }

    public boolean goesLeft() {
        return destination == Direction.leftTo(start);
    }

    public boolean turnsBack() {
        return destination == start;
    }
}
