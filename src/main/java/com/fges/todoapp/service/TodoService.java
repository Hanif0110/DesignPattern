package com.fges.todoapp.service;

import com.fges.todoapp.dataaccess.TodoDataAccess;
import org.apache.commons.cli.CommandLine;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// Service pour gérer la logique métier des todos
public class TodoService {
    private TodoDataAccess dataAccess;
    private Map<String, Consumer<CommandLine>> commandMap;

    public TodoService(String fileName) {
        this.dataAccess = new TodoDataAccess(fileName);
        this.commandMap = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        commandMap.put("insert", this::handleInsert);
        commandMap.put("list", this::handleList);
    }

    private void handleInsert(CommandLine cmd) {
        try {
            String todo = cmd.getOptionValue("todo");
            if (todo == null || todo.isEmpty()) {
                System.err.println("Missing TODO name. Use -todo option.");
                return;
            }
            dataAccess.insertTodo(todo);
            System.out.println("Todo added successfully.");
        } catch (IOException e) {
            System.err.println("Error adding todo: " + e.getMessage());
        }
    }

    private void handleList(CommandLine cmd) {
        try {
            dataAccess.listTodos();
        } catch (IOException e) {
            System.err.println("Error listing todos: " + e.getMessage());
        }
    }

    public int executeCommand(CommandLine cmd) {
        String command = cmd.getArgList().isEmpty() ? "" : cmd.getArgList().get(0);
        if (commandMap.containsKey(command)) {
            commandMap.get(command).accept(cmd);
            return 0;
        } else {
            System.err.println("Unknown command. Available commands are: insert, list");
            return 1;
        }
    }
}
