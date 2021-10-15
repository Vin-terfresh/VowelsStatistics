package com.ubs.integrationtests;

import com.ubs.RegularVowelStatisticsProcessor;
import com.ubs.VowelStatisticsProcessor;
import com.ubs.printer.RegularStatisticPrinter;
import com.ubs.printer.StatisticPrinter;
import com.ubs.statistics.vowels.RegularVowelsStatisticsGenerator;
import com.ubs.statistics.vowels.VowelsStatisticsGenerator;
import com.ubs.statistics.word.RegularWordStatisticsGenerator;
import com.ubs.statistics.word.WordStatisticsGenerator;
import com.ubs.words.RawLinesSupplier;
import com.ubs.words.RegularWordsSupplier;
import com.ubs.words.WordsSupplier;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VowelStatisticsIntegrationTest {

    @Test
    void testWholeChain() throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(output)) {

            RawLinesSupplier rawLinesSupplier = () -> Stream.of("Platon!made", "  -bAMboo:!! BOATS", ".");
            WordsSupplier wordsSupplier = new RegularWordsSupplier();
            WordStatisticsGenerator wordStatisticsGenerator = new RegularWordStatisticsGenerator();
            VowelsStatisticsGenerator vowelsStatisticsGenerator = new RegularVowelsStatisticsGenerator();
            StatisticPrinter statisticPrinter = new RegularStatisticPrinter(printStream);
            VowelStatisticsProcessor vowelStatisticsProcessor = new RegularVowelStatisticsProcessor(rawLinesSupplier,
                    wordsSupplier, wordStatisticsGenerator, vowelsStatisticsGenerator, statisticPrinter);
            vowelStatisticsProcessor.process();
            String printed = output.toString(StandardCharsets.UTF_8);
            assertTrue(printed.contains("({a, o}, 6) -> 2.5") || printed.contains("({o, a}, 6) -> 2.5"));
            assertTrue(printed.contains("({a, o}, 5) -> 2") || printed.contains("({o, a}, 5) -> 2"));
            assertTrue(printed.contains("({a, e}, 4) -> 2") || printed.contains("({e, a}, 4) -> 2"));
            assertEquals(3, printed.codePoints().filter(c -> c == '\n').count());
        }
    }

}