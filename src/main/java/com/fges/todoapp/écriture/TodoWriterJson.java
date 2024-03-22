package com.fges.todoapp.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.List;

public class TodoWriterJson implements TodoWriter {

    @Override
    public void writeTodo(String fileName, Todo todo) throws IOException {
        List<Todo> todos = readCurrentTodos(fileName);
        todos.add(todo);
        writeTodos(fileName, todos);
    }

    @Override
    public void writeTodos(String fileName, List<Todo> todos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String todosJsonString = mapper.writeValueAsString(todos);
        FileHandler.writeToFile(fileName, todosJsonString);
    }

    private List<Todo> readCurrentTodos(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String fileContent = FileHandler.readFileContent(fileName);
        // Utiliser un tableau vide par défaut si le fichier est vide pour éviter des exceptions
        if (fileContent.isEmpty()) {
            return List.of();
        }
        // Lecture et conversion du contenu JSON en liste de Todo
        Todo[] todosArray = mapper.readValue(fileContent, Todo[].class);
        return List.of(todosArray);
    }
}
