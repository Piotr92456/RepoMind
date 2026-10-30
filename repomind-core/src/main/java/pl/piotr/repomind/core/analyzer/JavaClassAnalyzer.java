package pl.piotr.repomind.core.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import pl.piotr.repomind.core.model.AnalysisResult;
import pl.piotr.repomind.core.model.ClassInfo;
import pl.piotr.repomind.core.model.ClassType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JavaClassAnalyzer {

    public AnalysisResult analyze(Path file)
            throws IOException {

        System.out.println("Parsing: " + file);

        AnalysisResult result =
                new AnalysisResult();

        DependencyAnalyzer dependencyAnalyzer =
                new DependencyAnalyzer();

        CompilationUnit cu =
                StaticJavaParser.parse(file);

        System.out.println("Parsed OK: " + file);

        String packageName =
                cu.getPackageDeclaration()
                        .map(p -> p.getNameAsString())
                        .orElse("");

        List<ClassInfo> classes =
                new ArrayList<>();

        for (TypeDeclaration<?> type : cu.getTypes()) {

            if (type instanceof ClassOrInterfaceDeclaration c) {

                ClassType classType =
                        c.isInterface()
                                ? ClassType.INTERFACE
                                : ClassType.CLASS;

                result.getClasses().add(
                        new ClassInfo(
                                c.getNameAsString(),
                                packageName + "." + c.getNameAsString(),
                                packageName,
                                classType,
                                c.getExtendedTypes().isEmpty()
                                        ? null
                                        : c.getExtendedTypes()
                                        .get(0)
                                        .getNameAsString(),
                                c.getImplementedTypes()
                                        .stream()
                                        .map(i -> i.getNameAsString())
                                        .toList()
                        )
                );

                result.getDependencies().addAll(
                        dependencyAnalyzer.analyze(c)
                );
            }
        }

        return result;
    }
}