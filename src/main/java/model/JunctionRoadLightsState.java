package model;

import java.util.HashMap;
import java.util.Map;

public class JunctionRoadLightsState {
    private final Map<Direction, RoadLight> roadLights;

    public JunctionRoadLightsState() {
        roadLights = new HashMap<>();
        for (var direction : Direction.values()) {
            roadLights.put(direction, new RoadLight());
        }
    }

    public Map<Direction, RoadLight> getRoadLights() {
        return roadLights;
    }
}
