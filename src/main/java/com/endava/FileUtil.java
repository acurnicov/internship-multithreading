package com.endava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtil {

    public static double getPriceAverage(String path) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.mapToDouble(product -> Double.parseDouble(product.split(",")[2]))
                    .average().getAsDouble();
        }
    }
}
