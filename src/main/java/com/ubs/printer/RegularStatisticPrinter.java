package com.ubs.printer;

import com.ubs.statistics.vowels.VowelsInWord;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class RegularStatisticPrinter implements StatisticPrinter {

    private static final String STATISTICS_FORMAT = "(%s, %d) -> %s\n";
    private static final DecimalFormat BIG_DECIMAL_FORMAT = new DecimalFormat("#.##");
    private final PrintStream output;

    @Override
    public void print(Map<VowelsInWord, BigDecimal> statistics) {
        statistics.forEach((key, value) -> output.printf(STATISTICS_FORMAT, setToString(key.getVowels()),
                key.getWordLength(), BIG_DECIMAL_FORMAT.format(value)));
    }

    private String setToString(Set<Character> vowels) {
        return vowels.stream()
                .map(String::valueOf)
                .collect(joining(", ", "{", "}"));
    }

}
