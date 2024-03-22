package com.fges.todoapp.dir;

import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.Optional;

public interface TodoManager {

    // Insère un nouveau Todo dans le fichier spécifié.
    void insertTodo(String fileName, Todo todo) throws IOException;

    // Liste tous les Todos depuis le fichier spécifié, avec la possibilité de filtrer les tâches accomplies ou non.
    void listTodos(String fileName, boolean showDone) throws IOException;

    // Supprime un Todo par son identifiant unique.
    void deleteTodoById(String fileName, int todoId) throws IOException;

    // Met à jour un Todo existant.
    void updateTodo(String fileName, Todo todo) throws IOException;

    // Récupère un Todo par son identifiant unique.
    Optional<Todo> getTodoById(String fileName, int todoId) throws IOException;
}
