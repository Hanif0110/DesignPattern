package com.fges.todoapp.dir;

import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.affichage.TodoPrinter;
import com.fges.todoapp.lecture.TodoReaderCsv;
import com.fges.todoapp.écriture.TodoWriterCsv;

import java.io.IOException;
import java.util.List;

public class CsvTodoManager implements TodoManager {

    private final TodoReaderCsv todoReaderCsv;
    private final TodoWriterCsv todoWriterCsv;
    private final TodoPrinter todoPrinter; // Adding a field for TodoPrinter

    // Updating the constructor to accept a TodoPrinter
    public CsvTodoManager(TodoReaderCsv todoReaderCsv, TodoWriterCsv todoWriterCsv, TodoPrinter todoPrinter) {
        this.todoReaderCsv = todoReaderCsv;
        this.todoWriterCsv = todoWriterCsv;
        this.todoPrinter = todoPrinter;
    }

    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        todoWriterCsv.writeTodo(fileName, todo);
    }

    @Override
    public void listTodos(String fileName, boolean showDone) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);
        List<Todo> todos = todoReaderCsv.readTodos(fileContent); // Read todos using TodoReaderCsv
        todoPrinter.printTodos(todos, showDone); // Print todos using TodoPrinter
    }
}
