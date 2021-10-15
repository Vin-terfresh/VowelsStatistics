package com.ubs.statistics.vowels;

import com.ubs.statistics.word.WordStatistics;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalAverageCollectorTest {

    @Test
    void testSupplier() {
        assertEquals(BigDecimal.ZERO, new BigDecimalAverageCollector().supplier().get().getAverage());
    }

    @Test
    void testAccumulator() {
        var collector = new BigDecimalAverageCollector();
        var accumulator = collector.supplier().get();
        collector.accumulator().accept(accumulator, new WordStatistics(Set.of(), 0, 1));
        assertEquals(0, accumulator.getAverage().compareTo(BigDecimal.ONE));
        collector.accumulator().accept(accumulator, new WordStatistics(Set.of(), 0, 4));
        assertEquals(0, accumulator.getAverage().compareTo(BigDecimal.valueOf(2.5)));
        collector.accumulator().accept(accumulator, new WordStatistics(Set.of(), 0, 33));
        var a = accumulator.getAverage();
        assertEquals(0, accumulator.getAverage().compareTo(BigDecimal.valueOf(12.67)));
    }

    @Test
    void testCombiner() {
        var collector = new BigDecimalAverageCollector();
        var accumulator = collector.supplier().get();
        var otherAccumulator = collector.supplier().get();
        assertEquals(collector.combiner().apply(accumulator, otherAccumulator).getAverage(), BigDecimal.ZERO);
        collector.accumulator().accept(accumulator, new WordStatistics(Set.of(), 0, 1));
        assertEquals(0, accumulator.getAverage().compareTo(BigDecimal.ONE));
        collector.accumulator().accept(otherAccumulator, new WordStatistics(Set.of(), 0, 4));
        assertEquals(0, collector.finisher().apply(collector.combiner().apply(accumulator, otherAccumulator)).compareTo(BigDecimal.valueOf(2.5)));
    }
}