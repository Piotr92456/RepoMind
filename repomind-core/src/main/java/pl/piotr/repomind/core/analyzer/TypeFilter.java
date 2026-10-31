package pl.piotr.repomind.core.analyzer;

import java.util.Set;

/**
 * Filtr typów, których nie chcemy
 * traktować jako zależności projektu.
 */
public final class TypeFilter {

    private static final Set<String> IGNORED_TYPES =
            Set.of(
                    "String",
                    "Long",
                    "Integer",
                    "Boolean",
                    "Double",
                    "Float",
                    "Byte",
                    "Short",
                    "Character",
                    "int",
                    "long",
                    "boolean",
                    "double",
                    "float",
                    "byte",
                    "short",
                    "char"
            );

    private TypeFilter() {
    }

    public static boolean shouldIgnore(
            String type
    ) {
        return IGNORED_TYPES.contains(type);
    }
}