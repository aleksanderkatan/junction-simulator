package model.intensityOptimized;

import model.Direction;
import model.JunctionRoadLights;
import model.RoadLightColor;

import java.util.Map;

public class JunctionRoadLightsStateWrapper {
    private final DoubleIterationCounter counter;
    private final JunctionRoadLights roadLights;

    public JunctionRoadLightsStateWrapper(JunctionRoadLights roadLights, DoubleIterationCounter counter) {
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
