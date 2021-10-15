package com.ubs.statistics.vowels;

import com.ubs.statistics.word.WordStatistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RegularVowelsStatisticsGeneratorTest {

    @ParameterizedTest
    @MethodSource("generateTestArguments")
    void testGenerateStatistics(Stream<WordStatistics> wordStatistics,
                                Map<VowelsInWord, BigDecimal> expectedVowelsStatistics) {
        VowelsStatisticsGenerator generator = new RegularVowelsStatisticsGenerator();
        assertEquals(expectedVowelsStatistics, generator.generateStatistics(wordStatistics));
    }

    private static Stream<Arguments> generateTestArguments() {
        return Stream.of(
                Arguments.of(Stream.empty(), Map.of()),
                Arguments.of(
                        Stream.of(new WordStatistics(Set.of('a', 'o'), 5, 3)),
                        Map.of(new VowelsInWord(Set.of('a', 'o'), 5), rounded(BigDecimal.valueOf(3)))
                ),
                Arguments.of(
                        Stream.of(
                                new WordStatistics(Set.of('a', 'o'), 5, 3),
                                new WordStatistics(Set.of('a', 'o'), 4, 1)
                        ),
                        Map.of(
                                new VowelsInWord(Set.of('a', 'o'), 5), rounded(BigDecimal.valueOf(3)),
                                new VowelsInWord(Set.of('a', 'o'), 4), rounded(BigDecimal.valueOf(1))
                        )
                ),
                Arguments.of(
                        Stream.of(
                                new WordStatistics(Set.of('a', 'o'), 5, 3),
                                new WordStatistics(Set.of('a', 'u', 'o'), 5, 4),
                                new WordStatistics(Set.of('a', 'o'), 5, 2)
                        ),
                        Map.of(
                                new VowelsInWord(Set.of('a', 'o'), 5), rounded(BigDecimal.valueOf(2.5)),
                                new VowelsInWord(Set.of('a', 'u', 'o'), 5), rounded(BigDecimal.valueOf(4))
                        )
                )
        );
    }

    private static BigDecimal rounded(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}