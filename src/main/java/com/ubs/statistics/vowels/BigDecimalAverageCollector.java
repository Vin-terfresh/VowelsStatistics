package com.ubs.statistics.vowels;

import com.ubs.statistics.word.WordStatistics;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public final class BigDecimalAverageCollector implements Collector<WordStatistics, BigDecimalAverageCollector.AverageAccumulator, BigDecimal> {
    @Override
    public Supplier<AverageAccumulator> supplier() {
        return AverageAccumulator::new;
    }

    @Override
    public BiConsumer<AverageAccumulator, WordStatistics> accumulator() {
        return AverageAccumulator::add;
    }

    @Override
    public BinaryOperator<AverageAccumulator> combiner() {
        return AverageAccumulator::combine;
    }

    @Override
    public Function<AverageAccumulator, BigDecimal> finisher() {
        return AverageAccumulator::getAverage;
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
        return Set.of();
    }

    protected static class AverageAccumulator {
        private Integer sum = 0;
        private Integer count = 0;

        public void add(WordStatistics statistics) {
            sum += statistics.getVowelsCount();
            count++;
        }

        public AverageAccumulator combine(AverageAccumulator other) {
            sum += other.sum;
            count += other.count;
            return this;
        }

        public BigDecimal getAverage() {
            return count == 0 ?
                    BigDecimal.ZERO :
                    BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(count), MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_UP);
        }
    }
}


