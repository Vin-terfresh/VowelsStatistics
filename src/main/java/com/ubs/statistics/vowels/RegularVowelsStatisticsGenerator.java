package com.ubs.statistics.vowels;

import com.ubs.statistics.word.WordStatistics;
import com.ubs.statistics.word.WordStatisticsGenerator;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class RegularVowelsStatisticsGenerator implements VowelsStatisticsGenerator {

    @Override
    public Map<VowelsInWord, BigDecimal> generateStatistics(Stream<WordStatistics> statistics) {
        return statistics
                .collect(groupingBy(
                        wordStatistics -> new VowelsInWord(wordStatistics.getVowels(), wordStatistics.getLength()),
                        new BigDecimalAverageCollector()
                ));

    }
}
