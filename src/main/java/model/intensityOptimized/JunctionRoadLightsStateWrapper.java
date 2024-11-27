package model.intensityOptimized;

import model.Direction;
import model.JunctionRoadLightsState;
import model.RoadLightColor;

import java.util.Map;

public class JunctionRoadLightsStateWrapper {
    private final DoubleIterationCounter counter;
    private final JunctionRoadLightsState roadLights;

    public JunctionRoadLightsStateWrapper(JunctionRoadLightsState roadLights) {
        this.roadLights = roadLights;
        this.counter = new DoubleIterationCounter(0, 0);
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
