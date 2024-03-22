package com.fges.todoapp.lecture;

import com.fges.todoapp.model.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TodoReaderCsv implements TodoReader {

    private static final String DELIMITER = ",,,"; // Définir le délimiteur comme constante
    private static final int EXPECTED_PARTS_LENGTH = 2; // Longueur attendue pour les parties d'une ligne

    @Override
    public List<Todo> readTodos(String content) {
        List<String> lines = Arrays.asList(content.split("\n"));

        return lines.stream()
                .map(this::parseLine) // Utilisation d'une méthode de référence pour la lisibilité
                .collect(Collectors.toList());
    }

    private Todo parseLine(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length == EXPECTED_PARTS_LENGTH) {
            String description = parts[0].trim();
            boolean isDone = Boolean.parseBoolean(parts[1].trim());
            return new Todo(description, isDone);
        } else {
            // Si vous préférez lancer une exception pour les lignes mal formées, décommentez la ligne ci-dessous
            // throw new IllegalArgumentException("Ligne mal formée : " + line);
            // Sinon, ignorer simplement les lignes mal formées :
            return null;
        }
    }
}
