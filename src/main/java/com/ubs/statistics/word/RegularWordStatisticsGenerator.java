package com.ubs.statistics.word;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class RegularWordStatisticsGenerator implements WordStatisticsGenerator {

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'y');

    @Override
    public WordStatistics createStatistics(String word) {
        List<Character> vowels = word.codePoints()
                .mapToObj(c -> (char) c)
                .filter(this::isVowel)
                .collect(toList());

        return new WordStatistics(Set.copyOf(vowels), word.length(), vowels.size());
    }

    private boolean isVowel(Character i) {
        return VOWELS.contains(i);
    }
}
