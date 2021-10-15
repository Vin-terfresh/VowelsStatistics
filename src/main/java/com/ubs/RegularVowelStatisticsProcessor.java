package com.ubs;

import com.ubs.printer.StatisticPrinter;
import com.ubs.statistics.vowels.VowelsStatisticsGenerator;
import com.ubs.statistics.word.WordStatisticsGenerator;
import com.ubs.words.RawLinesSupplier;
import com.ubs.words.WordsSupplier;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class RegularVowelStatisticsProcessor implements VowelStatisticsProcessor {

    private final RawLinesSupplier rawLinesSupplier;
    private final WordsSupplier wordsSupplier;
    private final WordStatisticsGenerator wordStatisticsGenerator;
    private final VowelsStatisticsGenerator vowelsStatisticsGenerator;
    private final StatisticPrinter printer;

    @Override
    public void process() throws IOException {
        var statistics = vowelsStatisticsGenerator.generateStatistics(
                wordsSupplier.words(rawLinesSupplier.lines())
                        .map(wordStatisticsGenerator::createStatistics)
        );
        printer.print(statistics);
    }
}
