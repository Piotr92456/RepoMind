package pl.piotr.repomind.generators.plantuml;

import pl.piotr.repomind.core.model.ClassInfo;

public class DiagramClassifier {

    public DiagramCategory classify(
            ClassInfo classInfo
    ) {

        return switch (
                classInfo.componentType()
                ) {

            case SERVICE ->
                    DiagramCategory.SERVICES;

            case REPOSITORY ->
                    DiagramCategory.REPOSITORIES;

            case ENTITY ->
                    DiagramCategory.ENTITIES;

            case CONTROLLER,
                 REST_CONTROLLER ->
                    DiagramCategory.CONTROLLERS;

            case VIEW ->
                    DiagramCategory.VIEWS;


            default ->
                    DiagramCategory.OTHER;
        };
    }
}