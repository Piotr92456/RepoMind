package pl.piotr.repomind.core.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class JavaFileFinder {

    public List<Path> findJavaFiles(Path root)
            throws IOException {

        try (Stream<Path> stream = Files.walk(root)) {

            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .filter(path -> !path.toString().contains("\\build\\"))
                    .filter(path -> !path.toString().contains("\\target\\"))
                    .filter(path -> !path.toString().contains("\\.gradle\\"))
                    .toList();
        }
    }
}