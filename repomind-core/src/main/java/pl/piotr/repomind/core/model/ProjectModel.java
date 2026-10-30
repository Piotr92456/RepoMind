package pl.piotr.repomind.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Główny model projektu tworzony
 * podczas procesu analizy.
 *
 * Wszystkie analizatory zapisują
 * wyniki właśnie do tej struktury.
 *
 * W kolejnych wersjach będą tu
 * przechowywane również:
 *
 * - moduły
 * - kontrolery
 * - serwisy
 * - encje
 * - diagramy
 * - metadane AI
 */
public class ProjectModel {

    /**
     * Wszystkie wykryte klasy.
     */
    private final List<ClassInfo> classes =
            new ArrayList<>();

    /**
     * Wszystkie wykryte zależności.
     */
    private final List<DependencyInfo> dependencies =
            new ArrayList<>();

    /**
     * Zwraca listę klas projektu.
     *
     * @return lista klas
     */
    public List<ClassInfo> getClasses() {
        return classes;
    }

    /**
     * Zwraca listę zależności projektu.
     *
     * @return lista zależności
     */
    public List<DependencyInfo> getDependencies() {
        return dependencies;
    }
}