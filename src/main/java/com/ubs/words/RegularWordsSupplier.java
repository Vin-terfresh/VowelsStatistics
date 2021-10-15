package com.ubs.words;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RegularWordsSupplier implements WordsSupplier {

    private static final String WORDS_SPLIT_EXPRESSION = "\\W+";
    @Override
    public Stream<String> words(Stream<String> lines) throws IOException {
        return lines
                .flatMap(line ->Arrays.stream(line.split(WORDS_SPLIT_EXPRESSION)))
                .filter(Predicate.not(String::isEmpty))
                .map(String::toLowerCase);
    }
}
