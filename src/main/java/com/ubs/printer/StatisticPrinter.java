package com.ubs.printer;

import com.ubs.statistics.vowels.VowelsInWord;

import java.math.BigDecimal;
import java.util.Map;

public interface StatisticPrinter {
    void print(Map<VowelsInWord, BigDecimal> statistics);
}
