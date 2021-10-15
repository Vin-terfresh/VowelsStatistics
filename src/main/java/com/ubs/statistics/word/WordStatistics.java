package com.ubs.statistics.word;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class WordStatistics {
    private final Set<Character> vowels;
    private final int length;
    private final int vowelsCount;
}
