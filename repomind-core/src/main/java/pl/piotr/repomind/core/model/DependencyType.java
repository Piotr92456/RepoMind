package pl.piotr.repomind.core.model;

/**
 * Reprezentuje typ zależności pomiędzy
 * dwoma elementami projektu.
 *
 * W kolejnych wersjach RepoMind
 * lista będzie rozszerzana o nowe
 * rodzaje relacji wykrywane podczas
 * analizy kodu źródłowego.
 */
public enum DependencyType {

    /**
     * Dziedziczenie klasy.
     *
     * class A extends B
     */
    EXTENDS,

    /**
     * Implementacja interfejsu.
     *
     * class A implements B
     */
    IMPLEMENTS,

    /**
     * Zależność przez pole klasy.
     *
     * private UserService userService;
     */
    FIELD,

    /**
     * Zależność przez parametr metody.
     */
    METHOD_PARAMETER,

    /**
     * Zależność zwracana przez metodę.
     */
    METHOD_RETURN,

    /**
     * Wykryte wywołanie metody.
     */
    METHOD_CALL,

    /**
     * Wstrzyknięcie przez konstruktor.
     */
    CONSTRUCTOR_INJECTION,

    /**
     * Relacja wynikająca z użycia adnotacji.
     */
    ANNOTATION
}