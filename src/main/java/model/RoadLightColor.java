package model;

public enum RoadLightColor {
    RED, RED_YELLOW, GREEN, YELLOW;

    boolean allowsDriving() {
        return this == GREEN || this == YELLOW;
    }
}
