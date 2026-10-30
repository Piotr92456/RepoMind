package pl.piotr.repomind.core.scanner;

import pl.piotr.repomind.core.analyzer.JavaClassAnalyzer;
import pl.piotr.repomind.core.model.AnalysisResult;
import pl.piotr.repomind.core.model.ProjectModel;

import java.io.IOException;
import java.nio.file.Path;

public class ProjectScanner {

    public ProjectModel scan(Path root)
            throws IOException {

        ProjectModel model =
                new ProjectModel();

        JavaFileFinder fileFinder =
                new JavaFileFinder();

        JavaClassAnalyzer analyzer =
                new JavaClassAnalyzer();

        for (Path file : fileFinder.findJavaFiles(root)) {

            try {

                AnalysisResult result =
                        analyzer.analyze(file);

                model.getClasses()
                        .addAll(
                                result.getClasses()
                        );

                model.getDependencies()
                        .addAll(
                                result.getDependencies()
                        );

            } catch (Exception e) {

                System.err.println(
                        "\nCannot parse: "
                                + file
                );

                System.err.println(
                        "Exception type: "
                                + e.getClass().getName()
                );

                System.err.println(
                        "Message: "
                                + e.getMessage()
                );

                e.printStackTrace();
            }

        }

        return model;
    }
}