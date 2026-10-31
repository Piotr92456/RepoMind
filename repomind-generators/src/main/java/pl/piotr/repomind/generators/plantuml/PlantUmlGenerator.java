package pl.piotr.repomind.generators.plantuml;

import pl.piotr.repomind.core.model.ClassInfo;
import pl.piotr.repomind.core.model.DependencyInfo;
import pl.piotr.repomind.core.model.ProjectModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PlantUmlGenerator {

    public void generate(
            ProjectModel model,
            Path outputDirectory
    ) throws IOException {

        Files.createDirectories(
                outputDirectory
        );

        Set<String> services =
                new HashSet<>();

        Set<String> repositories =
                new HashSet<>();

        Set<String> views =
                new HashSet<>();

        Set<String> entities =
                new HashSet<>();

        Set<String> controllers =
                new HashSet<>();

        Set<String> all =
                new HashSet<>();

        DiagramClassifier classifier =
                new DiagramClassifier();

        Set<String> projectClasses =
                model.getClasses()
                        .stream()
                        .map(ClassInfo::name)
                        .collect(Collectors.toSet());


        System.out.println(
                "Project classes: "
                        + projectClasses.size()
        );

        for (DependencyInfo dependency
                : model.getDependencies()) {

            if (!projectClasses.contains(
                    dependency.source()
            )) {
                continue;
            }

            if (!projectClasses.contains(
                    dependency.target()
            )) {
                continue;
            }

            DiagramCategory category =
                    classifier.classify(
                            dependency.source()
                    );

            String relation =
                    dependency.target()
                            + " "
                            + PlantUmlRelationMapper.map(
                            dependency.type()
                    )
                            + " "
                            + dependency.source();

            addToCategory(
                    category,
                    relation,
                    services,
                    repositories,
                    views,
                    entities,
                    controllers
            );

            all.add(relation);
        }

        System.out.println(
                "Services: " + services.size()
        );

        System.out.println(
                "Repositories: " + repositories.size()
        );

        System.out.println(
                "Views: " + views.size()
        );

        System.out.println(
                "Entities: " + entities.size()
        );

        System.out.println(
                "Controllers: " + controllers.size()
        );

        System.out.println(
                "Total relations: " + all.size()
        );

        writeDiagram(
                outputDirectory,
                "services.puml",
                services
        );

        writeDiagram(
                outputDirectory,
                "repositories.puml",
                repositories
        );

        writeDiagram(
                outputDirectory,
                "views.puml",
                views
        );

        writeDiagram(
                outputDirectory,
                "entities.puml",
                entities
        );

        writeDiagram(
                outputDirectory,
                "controllers.puml",
                controllers
        );

        writeDiagram(
                outputDirectory,
                "project-summary.puml",
                all
        );
    }

    private void writeDiagram(
            Path outputDirectory,
            String fileName,
            Set<String> relations
    ) throws IOException {

        System.out.println(
                "Writing file: "
                        + outputDirectory
                        .resolve(fileName)
                        .toAbsolutePath()
        );

        StringBuilder builder =
                new StringBuilder();

        builder.append("@startuml")
                .append(System.lineSeparator())
                .append(System.lineSeparator());

        relations.stream()
                .sorted()
                .forEach(relation ->

                        builder.append(relation)
                                .append(System.lineSeparator())
                );

        builder.append(System.lineSeparator())
                .append("@enduml");

        Files.writeString(
                outputDirectory.resolve(
                        fileName
                ),
                builder.toString()
        );
    }

    private void addToCategory(
            DiagramCategory category,
            String relation,
            Set<String> services,
            Set<String> repositories,
            Set<String> views,
            Set<String> entities,
            Set<String> controllers
    ) {

        switch (category) {

            case SERVICES:
                services.add(relation);
                break;

            case REPOSITORIES:
                repositories.add(relation);
                break;

            case VIEWS:
                views.add(relation);
                break;

            case ENTITIES:
                entities.add(relation);
                break;

            case CONTROLLERS:
                controllers.add(relation);
                break;

            default:
                break;
        }
    }
}