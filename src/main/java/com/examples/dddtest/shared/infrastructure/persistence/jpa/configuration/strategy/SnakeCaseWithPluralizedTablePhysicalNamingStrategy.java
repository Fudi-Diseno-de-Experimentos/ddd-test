package com.examples.dddtest.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    // Converts a table name to snake_case and pluralizes it

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {

        if (name == null) return null;
        String snakeCase = toSnakeCase(name.getText());
        String pluralized = pluralize(snakeCase);
        return Identifier.toIdentifier(pluralized);
    }

    // Converts a column name to snake_case.

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (name == null) return null;
        return Identifier.toIdentifier(toSnakeCase(name.getText()));
    }

    // Converts camelCase or PascalCase to snake_case.

    private String toSnakeCase(String input) {
        return input
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z\\d])([A-Z])", "$1_$2")
                .toLowerCase();
    }

    //Basic English pluralization for table names.

    private String pluralize(String word) {
        if (word == null || word.isBlank()) return word;

        if (word.endsWith("s") || word.endsWith("x") || word.endsWith("z")
                || word.endsWith("ch") || word.endsWith("sh")) {
            return word + "es";
        }

        if (word.endsWith("y") && word.length() > 1
                && !isVowel(word.charAt(word.length() - 2))) {
            return word.substring(0, word.length() - 1) + "ies";
        }

        return word + "s";
    }

    private boolean isVowel(char c) {
        return "aeiou".indexOf(Character.toLowerCase(c)) >= 0;
    }
}
