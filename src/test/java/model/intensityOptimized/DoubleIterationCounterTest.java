package model.intensityOptimized;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static model.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class DoubleIterationCounterTest {
    @Test
    void returnsNothingWhenBothZero() {
        DoubleIterationCounter counter = new DoubleIterationCounter(0, 0);

        assertEquals(Optional.empty(), counter.step());
    }

    @Test
    void returnsNorthWhenFirstArgumentIsZero() {
        DoubleIterationCounter counter = new DoubleIterationCounter(2, 0);

        assertEquals(Optional.of(NORTH), counter.step());
        assertEquals(Optional.of(NORTH), counter.step());
        assertEquals(Optional.empty(), counter.step());
    }

    @Test
    void returnsEastWhenSecondArgumentIsZero() {
        DoubleIterationCounter counter = new DoubleIterationCounter(0, 2);

        assertEquals(Optional.of(EAST), counter.step());
        assertEquals(Optional.of(EAST), counter.step());
        assertEquals(Optional.empty(), counter.step());
    }

    @Test
    void returnsCorrectBothArgumentsAreBiggerThanZero() {
        DoubleIterationCounter counter = new DoubleIterationCounter(1, 2);

        assertEquals(Optional.of(NORTH), counter.step());
        assertEquals(Optional.of(EAST), counter.step());
        assertEquals(Optional.of(EAST), counter.step());
        assertEquals(Optional.empty(), counter.step());
    }

    @Test
    void returnsCorrectHasFinished() {
        DoubleIterationCounter counter = new DoubleIterationCounter(1, 2);

        boolean f0 = counter.hasFinished();
        counter.step();
        boolean f1 = counter.hasFinished();
        counter.step();
        boolean f2 = counter.hasFinished();
        counter.step();
        boolean f3 = counter.hasFinished();
        counter.step();
        boolean f4 = counter.hasFinished();

        assertFalse(f0);
        assertFalse(f1);
        assertFalse(f2);
        assertTrue(f3);
        assertTrue(f4);
    }

}