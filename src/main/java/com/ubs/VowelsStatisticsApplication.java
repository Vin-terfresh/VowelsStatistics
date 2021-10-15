package com.ubs;

import com.ubs.printer.RegularStatisticPrinter;
import com.ubs.printer.StatisticPrinter;
import com.ubs.statistics.vowels.RegularVowelsStatisticsGenerator;
import com.ubs.statistics.vowels.VowelsStatisticsGenerator;
import com.ubs.statistics.word.RegularWordStatisticsGenerator;
import com.ubs.statistics.word.WordStatisticsGenerator;
import com.ubs.words.FileBasedRawLinesSupplier;
import com.ubs.words.RawLinesSupplier;
import com.ubs.words.RegularWordsSupplier;
import com.ubs.words.WordsSupplier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class VowelsStatisticsApplication {

    public static void main(String[] args) {
        try (PrintStream output = new PrintStream(new FileOutputStream("c:/tools/temp/output.txt"))) {
            RawLinesSupplier rawLinesSupplier = new FileBasedRawLinesSupplier("c:/tools/temp/input.txt");
            WordsSupplier wordsSupplier = new RegularWordsSupplier();
            WordStatisticsGenerator wordStatisticsGenerator = new RegularWordStatisticsGenerator();
            VowelsStatisticsGenerator generator = new RegularVowelsStatisticsGenerator();
            StatisticPrinter printer = new RegularStatisticPrinter(output);
            VowelStatisticsProcessor processor = new RegularVowelStatisticsProcessor(rawLinesSupplier,
                    wordsSupplier, wordStatisticsGenerator, generator, printer);
            processor.process();
        } catch (IOException e) {
            System.err.println("Exception has ben raised during execution: " + e);
        }
    }
}
