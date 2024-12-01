package model.intensityOptimized;

import model.Direction;
import model.RoadLightColor;
import model.RoadLights;

import java.util.Map;

public class JunctionRoadLightsStateWrapper {
    private final DoubleIterationCounter counter;
    private final RoadLights roadLights;

    public JunctionRoadLightsStateWrapper(RoadLights roadLights, DoubleIterationCounter counter) {
        this.roadLights = roadLights;
        this.counter = counter;
    }

    public void step() {
        var possibleDirection = counter.step();
        possibleDirection.ifPresent(roadLights::switchLights);
        roadLights.step();
    }

    public Map<Direction, RoadLightColor> getRoadLights() {
        return roadLights.getRoadLights();
    }

    public boolean needsEnqueueing() {
        return counter.hasFinished();
    }

    public void enqueue(int northLength, int eastLength) {
        counter.refresh(northLength, eastLength);
    }
}
