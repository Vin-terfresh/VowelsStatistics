package com.ubs.words;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class RegularWordsSupplierTest {

    @Test
    void words() throws IOException {
        WordsSupplier supplier = new RegularWordsSupplier();
        assertTrue(supplier.words(Stream.empty()).findAny().isEmpty());
        assertEquals(List.of("onlyonewordwithstrangecapitalization"),
                supplier.words(Stream.of("ONlyOneWordWITHStrangeCapitalization")).collect(toList()));
        assertEquals(List.of("some", "words", "in", "a", "few", "lines", "split", "by", "spaces", "and", "punctuation"),
                supplier.words(Stream.of("Some...words?  in     a ", " : FeW-lines :split!!!by", "  spaces!? and;;-punctuation")).collect(toList()));
    }
}