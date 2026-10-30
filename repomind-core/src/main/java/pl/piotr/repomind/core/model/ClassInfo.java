package pl.piotr.repomind.core.model;

import java.util.List;

public record ClassInfo(
        String name,
        String qualifiedName,
        String packageName,
        ClassType type,
        String superClass,
        List<String> interfaces
) {
}
