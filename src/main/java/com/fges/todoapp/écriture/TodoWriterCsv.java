package com.fges.todoapp.écriture;

import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TodoWriterCsv implements TodoWriter {

    @Override
    public void writeTodo(String fileName, Todo todo) throws IOException {
        // Préparation de la chaîne à écrire pour un seul todo
        String todoStr = formatTodoForCsv(todo);

        // Lecture du contenu actuel du fichier
        String fileContent = FileHandler.readFileContent(fileName);

        // Ajout d'un saut de ligne si nécessaire
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todoStr;

        // Écriture du contenu modifié dans le fichier
        FileHandler.writeToFile(fileName, fileContent);
    }

    @Override
    public void writeTodos(String fileName, List<Todo> todos) throws IOException {
        // Lecture du contenu actuel du fichier
        String fileContent = FileHandler.readFileContent(fileName);

        // Préparation de la chaîne à écrire pour la liste de todos
        String todosStr = todos.stream()
                .map(this::formatTodoForCsv)
                .collect(Collectors.joining("\n"));

        // Ajout d'un saut de ligne si nécessaire
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todosStr;

        // Écriture du contenu modifié dans le fichier
        FileHandler.writeToFile(fileName, fileContent);
    }

    private String formatTodoForCsv(Todo todo) {
        // Formatage d'un Todo pour l'écriture en CSV
        return String.format("%s,,,%b", todo.getDescription(), todo.isDone());
    }
}
