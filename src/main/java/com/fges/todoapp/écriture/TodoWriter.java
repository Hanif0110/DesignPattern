package com.fges.todoapp.écriture;

import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.List;

public interface TodoWriter {

    // Écrit un seul Todo dans le fichier spécifié.
    void writeTodo(String fileName, Todo todo) throws IOException;

    // Écrit une liste de Todos dans le fichier spécifié.
    void writeTodos(String fileName, List<Todo> todos) throws IOException;
}
