package com.fges.todoapp.dataaccess;

import com.fges.todoapp.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Classe pour accéder aux données des todos
public class TodoDataAccess {
    private Path filePath;
    private ObjectMapper objectMapper;

    public TodoDataAccess(String fileName) {
        this.filePath = Paths.get(fileName);
        this.objectMapper = new ObjectMapper();
    }

    public void insertTodo(String todoName) throws IOException {
        List<Todo> todos = listTodosInternal();
        todos.add(new Todo(todoName));
        objectMapper.writeValue(filePath.toFile(), todos);
        System.out.println("Todo inserted successfully.");
    }

    public void listTodos() throws IOException {
        List<Todo> todos = listTodosInternal();
        todos.forEach(todo -> System.out.println(todo.getName()));
    }

    private List<Todo> listTodosInternal() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        return List.of(objectMapper.readValue(filePath.toFile(), Todo[].class));
    }
}
