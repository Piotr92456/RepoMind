package pl.piotr.repomind.core.model;

/**
 * Reprezentuje pojedynczą relację
 * pomiędzy dwoma elementami projektu.
 *
 * Przykład:
 *
 * UserService
 *     EXTENDS
 * BaseService
 *
 * source = UserService
 * target = BaseService
 * type   = EXTENDS
 */
public record DependencyInfo(

        /**
         * Klasa źródłowa relacji.
         */
        String source,

        /**
         * Klasa docelowa relacji.
         */
        String target,

        /**
         * Typ wykrytej relacji.
         */
        DependencyType type
) {
}