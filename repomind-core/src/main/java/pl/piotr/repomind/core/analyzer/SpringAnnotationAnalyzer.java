package pl.piotr.repomind.core.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import pl.piotr.repomind.core.model.SpringComponentType;

public class SpringAnnotationAnalyzer {

    public SpringComponentType analyze(
            ClassOrInterfaceDeclaration declaration
    ) {

        if (declaration.getNameAsString()
                .endsWith("Repository")) {

            System.out.println(
                    "Repository detected by name: "
                            + declaration.getNameAsString()
            );

            return SpringComponentType.REPOSITORY;
        }

        if (declaration.isAnnotationPresent("Service")) {
            return SpringComponentType.SERVICE;
        }

        if (declaration.isAnnotationPresent("Repository")) {
            return SpringComponentType.REPOSITORY;
        }

        if (declaration.isAnnotationPresent("RestController")) {
            return SpringComponentType.REST_CONTROLLER;
        }

        if (declaration.isAnnotationPresent("Controller")) {
            return SpringComponentType.CONTROLLER;
        }

        if (declaration.isAnnotationPresent("Entity")) {
            return SpringComponentType.ENTITY;
        }

        if (declaration.isAnnotationPresent("Component")) {
            return SpringComponentType.COMPONENT;
        }

        if (declaration.isAnnotationPresent("Configuration")) {
            return SpringComponentType.CONFIGURATION;
        }

        if (declaration.isAnnotationPresent("Route")) {
            return SpringComponentType.VIEW;
        }

        boolean springDataRepository =
                declaration.getExtendedTypes()
                        .stream()
                        .map(type -> type.getNameAsString())
                        .anyMatch(name ->

                                name.endsWith("Repository")
                                        || name.equals("JpaRepository")
                                        || name.equals("JpaSpecificationExecutor")
                                        || name.equals("CrudRepository")
                                        || name.equals("PagingAndSortingRepository")
                        );

        if (springDataRepository) {
            return SpringComponentType.REPOSITORY;
        }

        if (declaration.getNameAsString()
                .contains("Repository")) {

            System.out.println(
                    "Repository candidate: "
                            + declaration.getNameAsString()
            );

            declaration.getExtendedTypes()
                    .forEach(type ->

                            System.out.println(
                                    "  extends -> "
                                            + type.getNameAsString()
                            )
                    );
        }

        return SpringComponentType.UNKNOWN;
    }
}