package com.ubs.words;

import java.io.IOException;
import java.util.stream.Stream;

public interface RawLinesSupplier {

    Stream<String> lines() throws IOException;
}
