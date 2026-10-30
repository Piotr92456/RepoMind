package pl.piotr.repomind.plugin.api;

import pl.piotr.repomind.core.model.ProjectModel;

import java.nio.file.Path;

public interface GeneratorPlugin {

    String getName();

    void generate(
            ProjectModel model,
            Path outputDirectory
    );
}
