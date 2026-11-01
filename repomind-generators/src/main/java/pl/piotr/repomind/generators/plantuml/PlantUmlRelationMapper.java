package pl.piotr.repomind.generators.plantuml;

import pl.piotr.repomind.core.model.DependencyType;

/**
 * Mapuje typ zależności RepoMind
 * na składnię PlantUML.
 */
public final class PlantUmlRelationMapper {

    private PlantUmlRelationMapper() {
    }

    public static String map(
            DependencyType dependencyType
    ) {

        return switch (dependencyType) {

            case EXTENDS ->
                    "<|--";

            case IMPLEMENTS ->
                    "<|..";

            case FIELD,
                 CONSTRUCTOR_INJECTION ->
                    "-->";

            case METHOD_CALL ->
                    "..>";

            default ->
                    "-->";
        };
    }
}