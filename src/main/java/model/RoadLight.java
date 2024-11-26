package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static model.RoadLightColor.*;

public class RoadLight {
    private RoadLightColor state;
    private Queue<RoadLightColor> stateQueue;

    public RoadLight() {
        state = RED;
    }

    public void step() {
        if (stateQueue.size() == 0) {
            return;
        }
        state = stateQueue.remove();
    }

    public boolean isQueued() {
        return !stateQueue.isEmpty();
    }

    public void turnRed() {
        if (isQueued() || state == RED) {
            return;
        }
        stateQueue = new LinkedList<>(Arrays.asList(YELLOW, RED));
    }

    public void turnGreen() {
        if (isQueued() || state == RED) {
            return;
        }
        stateQueue = new LinkedList<>(Arrays.asList(RED_YELLOW, GREEN));
    }
}
