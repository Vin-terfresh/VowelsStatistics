package com.ubs.words;

import java.io.IOException;
import java.util.stream.Stream;

public interface WordsSupplier {

    Stream<String> words(Stream<String> lines) throws IOException;
}
