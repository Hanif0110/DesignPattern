package com.fges.todoapp.service;

import com.fges.todoapp.dataaccess.TodoDataAccess;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.util.List;

// Service pour gérer la logique métier des todos
public class TodoService {
    private TodoDataAccess dataAccess;

    public TodoService(String fileName) {
        this.dataAccess = new TodoDataAccess(fileName);
    }

    public int executeCommand(CommandLine cmd) {
        List<String> args = cmd.getArgList();
        if (args.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = args.get(0);
        try {
            switch (command) {
                case "insert":
                    // Insère un nouveau _todo
                    if (args.size() < 2) {
                        System.err.println("Missing TODO name");
                        return 1;
                    }
                    dataAccess.insertTodo(args.get(1));
                    System.out.println("Todo added successfully.");
                    return 0;
                case "list":
                    // Liste tous les todos
                    dataAccess.listTodos();
                    return 0;
                default:
                    System.err.println("Unknown command");
                    return 1;
            }
        } catch (IOException e) {
            System.err.println("Error executing command: " + e.getMessage());
            return 1;
        }
    }
}
