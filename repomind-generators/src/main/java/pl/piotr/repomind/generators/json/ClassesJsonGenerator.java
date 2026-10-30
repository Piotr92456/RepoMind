package pl.piotr.repomind.generators.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pl.piotr.repomind.core.model.ProjectModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClassesJsonGenerator {

    public void generate(
            ProjectModel model,
            Path outputDirectory
    ) throws IOException {

        Files.createDirectories(outputDirectory);

        ObjectMapper mapper =
                new ObjectMapper();

        mapper.enable(
                SerializationFeature.INDENT_OUTPUT
        );

        mapper.writeValue(
                outputDirectory
                        .resolve("classes.json")
                        .toFile(),
                model.getClasses()
        );
    }
}