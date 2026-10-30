package pl.piotr.repomind.core.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import pl.piotr.repomind.core.model.DependencyInfo;
import pl.piotr.repomind.core.model.DependencyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Odpowiada za wykrywanie relacji
 * pomiędzy klasami.
 *
 * RepoMind 0.2.0:
 * - extends
 * - implements
 */
public class DependencyAnalyzer {

    /**
     * Analizuje klasę lub interfejs
     * i zwraca wykryte zależności.
     *
     * @param declaration deklaracja klasy
     * @return lista zależności
     */
    public List<DependencyInfo> analyze(
            ClassOrInterfaceDeclaration declaration
    ) {

        List<DependencyInfo> dependencies =
                new ArrayList<>();

        String source =
                declaration.getNameAsString();

        declaration.getExtendedTypes()
                .forEach(type -> {
                    System.out.println(
                            "[EXTENDS] "
                                    + source
                                    + " -> "
                                    + type.getNameAsString()

                    );
                    dependencies.add(
                            new DependencyInfo(
                                    source,
                                    type.getNameAsString(),
                                    DependencyType.EXTENDS
                            )
                    );
                });

        declaration.getImplementedTypes()
                .forEach(type -> {

                    System.out.println(
                            "[IMPLEMENTS] "
                                    + source
                                    + " -> "
                                    + type.getNameAsString()
                    );
                    dependencies.add(
                            new DependencyInfo(
                                    source,
                                    type.getNameAsString(),
                                    DependencyType.IMPLEMENTS
                            )
                    );
                });

        return dependencies;
    }
}