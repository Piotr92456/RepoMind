package pl.piotr.repomind.core.parser;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;

public final class ParserFactory {

    private ParserFactory() {
    }

    public static void configure() {

        StaticJavaParser.getConfiguration()
                .setLanguageLevel(
                        ParserConfiguration.LanguageLevel.JAVA_21
                );
    }
}
