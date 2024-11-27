package model.cyclic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    @Test
    void ticksAtCorrectIntervals() {
        var counter = new Counter(3);

        assertFalse(counter.step());
        assertFalse(counter.step());
        assertTrue(counter.step());
        assertFalse(counter.step());
        assertFalse(counter.step());
        assertTrue(counter.step());
    }

}