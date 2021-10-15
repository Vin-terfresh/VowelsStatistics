package com.ubs.printer;

import com.ubs.statistics.vowels.VowelsInWord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularStatisticPrinterTest {

    @ParameterizedTest
    @MethodSource("printTestArguments")
    void testPrintEmptyStatistics(Map<VowelsInWord, BigDecimal> statistics, String expectedOutput) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(output)) {
            new RegularStatisticPrinter(printStream).print(statistics);
            assertEquals(output.toString(StandardCharsets.UTF_8), expectedOutput);
        }
    }

    static Stream<Arguments> printTestArguments() {
        Map<VowelsInWord, BigDecimal> vowelsStatistics = new LinkedHashMap<>();
        vowelsStatistics.put(new VowelsInWord(linkedSet(List.of('a', 'u', 'e')), 5), BigDecimal.valueOf(2));
        vowelsStatistics.put(new VowelsInWord(linkedSet(List.of('o')), 3), BigDecimal.valueOf(1.23));
        return Stream.of(
                Arguments.of(Map.of(), ""),
                Arguments.of(
                        Map.of(new VowelsInWord(linkedSet(List.of('a', 'u', 'e')), 5), BigDecimal.valueOf(2.7)),
                        "({a, u, e}, 5) -> 2.7\n"
                ),
                Arguments.of(
                        Map.of(new VowelsInWord(linkedSet(List.of('a', 'u', 'e')), 5), BigDecimal.valueOf(2.7)),
                        "({a, u, e}, 5) -> 2.7\n"
                ),

                Arguments.of(
                        vowelsStatistics,
                        "({a, u, e}, 5) -> 2\n({o}, 3) -> 1.23\n"
                )
        );
    }

    private static Set<Character> linkedSet(List<Character> chars) {
        return new LinkedHashSet<>(chars);
    }
}