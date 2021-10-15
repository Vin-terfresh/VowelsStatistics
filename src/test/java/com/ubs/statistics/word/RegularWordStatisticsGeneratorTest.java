package com.ubs.statistics.word;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RegularWordStatisticsGeneratorTest {

    @ParameterizedTest
    @MethodSource("statisticsTestArguments")
    void createStatistics(String word, Set<Character> expectedVowels, int expectedLength, int expectedVowelsCount) {
        WordStatisticsGenerator wordStatisticsGenerator = new RegularWordStatisticsGenerator();
        WordStatistics statistics = wordStatisticsGenerator.createStatistics(word);
        assertEquals(expectedVowels, statistics.getVowels());
        assertEquals(expectedLength, statistics.getLength());
        assertEquals(expectedVowelsCount, statistics.getVowelsCount());
    }

    static Stream<Arguments> statisticsTestArguments() {
        return Stream.of(
                Arguments.of("", Set.of(), 0, 0),
                Arguments.of("krk", Set.of(), 3, 0),
                Arguments.of("cat", Set.of('a'), 3, 1),
                Arguments.of("beer", Set.of('e'), 4, 2),
                Arguments.of("cartoon", Set.of('a', 'o'), 7, 3),
                Arguments.of("facetiously", Set.of('a', 'e', 'i', 'o', 'u', 'y'), 11, 6)
        );
    }
}