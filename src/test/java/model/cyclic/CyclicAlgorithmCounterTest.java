package model.cyclic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CyclicAlgorithmCounterTest {
    @Test
    void ticksAtCorrectIntervals() {
        var counter = new CyclicCounter(3);

        assertFalse(counter.step());
        assertFalse(counter.step());
        assertTrue(counter.step());
        assertFalse(counter.step());
        assertFalse(counter.step());
        assertTrue(counter.step());
    }

}