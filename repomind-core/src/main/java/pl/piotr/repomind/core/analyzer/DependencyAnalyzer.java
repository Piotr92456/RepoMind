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
 * - field injection
 * - constructor injection
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

        analyzeFields(
                declaration,
                dependencies
        );

        analyzeConstructors(
                declaration,
                dependencies
        );

        return dependencies;
    }

    /**
     * Analiza pól klasy.
     */
    private void analyzeFields(
            ClassOrInterfaceDeclaration declaration,
            List<DependencyInfo> dependencies
    ) {

        String source =
                declaration.getNameAsString();

        declaration.getFields()
                .forEach(field ->

                        field.getVariables()
                                .forEach(variable -> {

                                    String target =
                                            variable.getTypeAsString();

                                    if (TypeFilter.shouldIgnore(target)) {
                                        return;
                                    }

                                    dependencies.add(
                                            new DependencyInfo(
                                                    source,
                                                    target,
                                                    DependencyType.FIELD
                                            )
                                    );
                                })
                );
    }

    /**
     * Analiza wstrzykiwania przez konstruktor.
     */
    private void analyzeConstructors(
            ClassOrInterfaceDeclaration declaration,
            List<DependencyInfo> dependencies
    ) {

        String source =
                declaration.getNameAsString();

        declaration.getConstructors()
                .forEach(constructor ->

                        constructor.getParameters()
                                .forEach(parameter -> {

                                    String target =
                                            parameter.getTypeAsString();

                                    if (TypeFilter.shouldIgnore(target)) {
                                        return;
                                    }

                                    dependencies.add(
                                            new DependencyInfo(
                                                    source,
                                                    target,
                                                    DependencyType.CONSTRUCTOR_INJECTION
                                            )
                                    );
                                })
                );
    }
}