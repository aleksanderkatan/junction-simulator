package model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JunctionRoadLightsState {
    private final Map<Direction, RoadLight> roadLights;

    public JunctionRoadLightsState() {
        roadLights = new HashMap<>();
        for (var direction : Direction.values()) {
            roadLights.put(direction, new RoadLight());
        }
    }

    public void switchLights() {
        for (var roadLight : roadLights.values()) {
            roadLight.switchColor();
        }
    }

    public Map<Direction, RoadLightColor> getRoadLights() {
        return roadLights.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getState()
                ));
    }
}
