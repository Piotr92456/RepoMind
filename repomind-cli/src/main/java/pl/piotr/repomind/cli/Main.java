package pl.piotr.repomind.cli;

import pl.piotr.repomind.core.model.ProjectModel;
import pl.piotr.repomind.core.parser.ParserConfigurationFactory;
import pl.piotr.repomind.core.scanner.ProjectScanner;
import pl.piotr.repomind.generators.json.ClassesJsonGenerator;
import pl.piotr.repomind.generators.json.DependenciesJsonGenerator;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args)
            throws Exception {

        ParserConfigurationFactory.configure();

        if (args.length == 0) {

            System.out.println("""
                    Usage:
                    repomind <project-path>
                    """);

            return;
        }

        Path projectPath =
                Path.of(args[0]);

        System.out.println(
                "Scanning project..."
        );

        ProjectScanner scanner =
                new ProjectScanner();

        ProjectModel model =
                scanner.scan(projectPath);

        System.out.println(
                "Classes found: "
                        + model.getClasses().size()
        );

        Path architectureDir =
                Path.of("architecture");

        new ClassesJsonGenerator()
                .generate(
                        model,
                        architectureDir
                );

        new DependenciesJsonGenerator()
                .generate(
                        model,
                        architectureDir
                );

        System.out.println(
                "Classes: "
                        + model.getClasses().size()
        );

        System.out.println(
                "Dependencies: "
                        + model.getDependencies().size()
        );
    }
}