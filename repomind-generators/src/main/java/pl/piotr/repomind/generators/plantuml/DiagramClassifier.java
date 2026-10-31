package pl.piotr.repomind.generators.plantuml;

/**
 * Klasyfikuje klasy do diagramów.
 */
public class DiagramClassifier {

    public DiagramCategory classify(
            String className
    ) {

        String lower =
                className.toLowerCase();

        if (lower.contains("service")) {
            return DiagramCategory.SERVICES;
        }

        if (lower.contains("repository")) {
            return DiagramCategory.REPOSITORIES;
        }

        if (lower.contains("view")) {
            return DiagramCategory.VIEWS;
        }

        if (lower.contains("entity")) {
            return DiagramCategory.ENTITIES;
        }

        if (lower.contains("controller")) {
            return DiagramCategory.CONTROLLERS;
        }

        return DiagramCategory.OTHER;
    }
}