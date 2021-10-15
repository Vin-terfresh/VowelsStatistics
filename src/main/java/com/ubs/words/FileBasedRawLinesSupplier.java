package com.ubs.words;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileBasedRawLinesSupplier implements RawLinesSupplier {

    private final String filePath;

    @Override
    public Stream<String> lines() throws IOException {
        return Files.lines(Path.of(filePath));
    }
}
