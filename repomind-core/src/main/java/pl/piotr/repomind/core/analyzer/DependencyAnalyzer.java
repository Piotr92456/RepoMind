package pl.piotr.repomind.core.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import pl.piotr.repomind.core.model.DependencyInfo;
import pl.piotr.repomind.core.model.DependencyType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        analyzeMethodCalls(
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

    /**
     * Analiza wywołań metod.
     */
    /**
     * Analiza wywołań metod.
     */
    /**
     * Analiza wywołań metod.
     */
    private void analyzeMethodCalls(
            ClassOrInterfaceDeclaration declaration,
            List<DependencyInfo> dependencies
    ) {

        Map<String, String> fieldMap =
                buildFieldMap(
                        declaration
                );

        String source =
                declaration.getNameAsString();

        System.out.println(
                "[METHOD_SCAN] "
                        + source
        );

        declaration.findAll(
                MethodCallExpr.class
        ).forEach(call -> {

            System.out.println(
                    "[METHOD_CALL] "
                            + source
                            + " -> "
                            + call
            );

            System.out.println(
                    "[METHOD_NAME] "
                            + call.getNameAsString()
            );

            call.getScope()
                    .ifPresent(scope -> {

                        System.out.println(
                                "[METHOD_SCOPE] "
                                        + scope
                        );

                        String dependency =
                                fieldMap.get(
                                        scope.toString()
                                );

                        if (dependency != null) {
                            return;
                        }

                        if (TypeFilter.shouldIgnore(
                                dependency
                        )) {
                            return;
                        }

                            dependencies.add(
                                    new DependencyInfo(
                                            source,
                                            dependency,
                                            DependencyType.METHOD_CALL
                                    )
                            );

                    });
        });
    }

    private Map<String, String> buildFieldMap(
            ClassOrInterfaceDeclaration declaration
    ) {

        Map<String, String> fieldMap =
                new HashMap<>();

        declaration.getFields()
                .forEach(field ->

                        field.getVariables()
                                .forEach(variable ->

                                        fieldMap.put(
                                                variable.getNameAsString(),
                                                variable.getTypeAsString()
                                        )
                                )
                );

        return fieldMap;
    }
}