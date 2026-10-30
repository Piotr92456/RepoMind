package pl.piotr.repomind.core.parser;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;

/**
 * Konfiguruje JavaParser dla RepoMind.
 */
public final class ParserConfigurationFactory {

    private ParserConfigurationFactory() {
    }

   /**
     * Inicjalizacja parsera.
     *    */
    public static void configure() {

        StaticJavaParser.getConfiguration()
                .setLanguageLevel(
                       ParserConfiguration.LanguageLevel.JAVA_21
                );
   }
}