package com.fges.todoapp.dir;

import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.affichage.TodoPrinter;
import com.fges.todoapp.lecture.TodoReaderJson;
import com.fges.todoapp.écriture.TodoWriterJson;

import java.io.IOException;
import java.util.List;

public class JsonTodoManager implements TodoManager {

    private final TodoReaderJson todoReaderJson;
    private final TodoWriterJson todoWriterJson;
    private final TodoPrinter todoPrinter; // Adding a field for TodoPrinter

    // Updating the constructor to accept a TodoPrinter
    public JsonTodoManager(TodoReaderJson todoReaderJson, TodoWriterJson todoWriterJson, TodoPrinter todoPrinter) {
        this.todoReaderJson = todoReaderJson;
        this.todoWriterJson = todoWriterJson;
        this.todoPrinter = todoPrinter;
    }

    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        todoWriterJson.writeTodo(fileName, todo);
    }

    @Override
    public void listTodos(String fileName, boolean showDone) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);
        List<Todo> todos = todoReaderJson.readTodos(fileContent); // Read todos using TodoReaderJson
        todoPrinter.printTodos(todos, showDone); // Print todos using TodoPrinter
    }
}
