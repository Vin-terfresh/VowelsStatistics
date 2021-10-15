package com.ubs.statistics.vowels;

import lombok.Data;

import java.util.Set;

@Data
public class VowelsInWord {
    private final Set<Character> vowels;
    private final int wordLength;
}
