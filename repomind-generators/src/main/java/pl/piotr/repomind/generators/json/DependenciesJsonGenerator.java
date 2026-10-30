package pl.piotr.repomind.generators.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.piotr.repomind.core.model.ProjectModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Generuje dependencies.json.
 */
public class DependenciesJsonGenerator {

    private final ObjectMapper mapper =
            new ObjectMapper()
                    .enable(
                            SerializationFeature.INDENT_OUTPUT
                    );

    /**
     * Zapisuje zależności projektu.
     *
     * @param model model projektu
     * @param outputDirectory katalog wyjściowy
     */
    public void generate(
            ProjectModel model,
            Path outputDirectory
    ) throws IOException {

        Files.createDirectories(
                outputDirectory
        );

        mapper.writeValue(
                outputDirectory
                        .resolve("dependencies.json")
                        .toFile(),
                model.getDependencies()
        );
    }
}