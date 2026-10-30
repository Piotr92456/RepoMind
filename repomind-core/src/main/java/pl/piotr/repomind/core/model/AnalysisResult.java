package pl.piotr.repomind.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Wynik analizy pojedynczego pliku źródłowego.
 *
 * Jeden plik może zawierać:
 * - wiele klas
 * - wiele interfejsów
 * - wiele zależności
 *
 * Wynik zostaje później scalony
 * do globalnego ProjectModel.
 */
public class AnalysisResult {

    private final List<ClassInfo> classes =
            new ArrayList<>();

    private final List<DependencyInfo> dependencies =
            new ArrayList<>();

    /**
     * Zwraca wykryte klasy.
     *
     * @return lista klas
     */
    public List<ClassInfo> getClasses() {
        return classes;
    }

    /**
     * Zwraca wykryte zależności.
     *
     * @return lista zależności
     */
    public List<DependencyInfo> getDependencies() {
        return dependencies;
    }
}