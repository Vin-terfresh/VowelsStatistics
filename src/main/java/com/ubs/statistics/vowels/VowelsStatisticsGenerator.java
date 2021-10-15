package com.ubs.statistics.vowels;

import com.ubs.statistics.word.WordStatistics;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

public interface VowelsStatisticsGenerator {
    Map<VowelsInWord, BigDecimal> generateStatistics(Stream<WordStatistics> words);
}
