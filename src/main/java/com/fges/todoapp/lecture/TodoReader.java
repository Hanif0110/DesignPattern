package com.fges.todoapp.lecture;

import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoReader {

    // Lit et retourne la liste de tous les Todos à partir du contenu fourni.
    List<Todo> readTodos(String content) throws IOException;

    // Lit et retourne un Todo spécifique à partir de son identifiant, si présent dans le contenu.
    Optional<Todo> readTodoById(String content, int todoId) throws IOException;

    // Filtre et retourne une liste de Todos qui correspondent à l'état spécifié (accompli ou non).
    List<Todo> filterTodosByDone(String content, boolean isDone) throws IOException;

    // Filtre et retourne une liste de Todos qui sont dus avant ou sur une date spécifique.
    List<Todo> filterTodosByDueDate(String content, LocalDate dueDate) throws IOException;

    // Filtre et retourne une liste de Todos qui contiennent une chaîne de caractères spécifique dans leur description.
    List<Todo> searchTodosByDescription(String content, String searchString) throws IOException;
}
