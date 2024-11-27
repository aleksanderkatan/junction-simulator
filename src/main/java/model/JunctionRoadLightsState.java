package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static model.Direction.*;
import static model.RoadLightColor.*;


public class JunctionRoadLightsState {
    private static final List<Map<Direction, RoadLightColor>> lightCycles = List.of(
            Map.of(NORTH, GREEN,        EAST, RED,          SOUTH, GREEN,       WEST, RED),     // may last multiple steps
            Map.of(NORTH, YELLOW,       EAST, RED,          SOUTH, YELLOW,      WEST, RED),
            Map.of(NORTH, RED,          EAST, RED_YELLOW,   SOUTH, RED,         WEST, RED_YELLOW),
            Map.of(NORTH, RED,          EAST, GREEN,        SOUTH, RED,         WEST, GREEN),   // may last multiple steps
            Map.of(NORTH, RED,          EAST, YELLOW,       SOUTH, RED,         WEST, YELLOW),
            Map.of(NORTH, RED_YELLOW,   EAST, RED,          SOUTH, RED_YELLOW,  WEST, RED)
    );

    private static final Set<Integer> stayStates = Set.of(0, 3);
    private int currentState = 0;
    private boolean isChanging = false;

    public JunctionRoadLightsState() {}

    public void step() {
        if (isChanging) {
            currentState = (currentState + 1) % lightCycles.size();
            if (stayStates.contains(currentState)) {
                isChanging = false;
            }
        }
    }

    public void switchLights() {
        if (isChanging) {
            return;
        }
        isChanging = true;
    }

    public void switchLights(Direction direction) {
        if (isChanging) {
            return;
        }
        if (direction == NORTH || direction == SOUTH) {
            if (currentState == 0) {
                return;
            }
            isChanging = true;
        }
        if (direction == EAST || direction == WEST) {
            if (currentState == 3) {
                return;
            }
            isChanging = true;
        }
    }

    public Map<Direction, RoadLightColor> getRoadLights() {
        return lightCycles.get(currentState);
    }
}
