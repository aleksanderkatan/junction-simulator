package model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static model.Direction.*;


// This needs refactoring, but I do not have enough time
// switchLights() does nothing if is called while a switch is already taking place
// RoadLight is written with assumption that all the lights switch at the same time
// RoadLight should probably just be inlined in this class
public class JunctionRoadLightsState {
    private final Map<Direction, RoadLight> roadLights;

    public JunctionRoadLightsState() {
        roadLights = new HashMap<>();
        for (var direction : Direction.values()) {
            roadLights.put(direction, new RoadLight());
        }
        roadLights.get(NORTH).switchColor();
        roadLights.get(SOUTH).switchColor();
    }

    public void step() {
        for (var roadLight : roadLights.values()) {
            roadLight.step();
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
