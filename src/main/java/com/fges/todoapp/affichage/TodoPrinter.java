package com.fges.todoapp.affichage;

import com.fges.todoapp.model.Todo;
import com.fges.todoapp.lecture.TodoReader;
import com.fges.todoapp.lecture.TodoReaderCsv;
import com.fges.todoapp.lecture.TodoReaderJson;

import java.io.IOException;
import java.util.List;

public class TodoPrinter {
    private final TodoReader jsonTodoReader;
    private final TodoReader csvTodoReader;
    private final TodoPrinterInterface consoleTodoPrinter;

    public TodoPrinter() {
        this.jsonTodoReader = new TodoReaderJson();
        this.csvTodoReader = new TodoReaderCsv();
        this.consoleTodoPrinter = new TodoPrinterConsole(); // Assuming TodoPrinterConsole implements TodoPrinterInterface
    }

    public void printTodos(List<Todo> todos, boolean showDone) {
        consoleTodoPrinter.print(todos, showDone);
    }

    public void printTodosFromJson(String jsonContent, boolean showDone) throws IOException {
        List<Todo> todos = jsonTodoReader.readTodos(jsonContent);
        printTodos(todos, showDone);
    }

    public void printTodosFromCsv(String csvContent, boolean showDone) throws IOException {
        List<Todo> todos = csvTodoReader.readTodos(csvContent);
        printTodos(todos, showDone);
    }
}

// Assuming this interface exists in your package
interface TodoPrinterInterface {
    void print(List<Todo> todos, boolean showDone);
}

// Assuming this class exists and implements the interface
class TodoPrinterConsole implements TodoPrinterInterface {
    @Override
    public void print(List<Todo> todos, boolean showDone) {
        // implementation of console printing
    }
}
