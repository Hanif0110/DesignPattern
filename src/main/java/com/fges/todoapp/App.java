package com.fges.todoapp;

import com.fges.todoapp.factory.TodoManagerFactory;
import com.fges.todoapp.io.CommandLineHandler;
import com.fges.todoapp.io.MigrateCommand;
import com.fges.todoapp.manager.TodoManager;
import com.fges.todoapp.model.Todo;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.List;

public class App {

    /**
     * Ne modifiez pas cette méthode.
     */
    public static void main(String[] args) {
        try {
            System.exit(exec(args));
        } catch (Exception e) {
            // Il est généralement une bonne pratique de gérer les exceptions à un certain niveau pour éviter les crashs.
            System.err.println("Une erreur est survenue : " + e.getMessage());
            System.exit(1);
        }
    }

    public static int exec(String[] args) throws IOException, ParseException {
        CommandLine cmd = CommandLineHandler.parseArguments(args);
        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Commande manquante");
            return 1;
        }

        String command = positionalArgs.get(0);
        TodoManager todoManager = TodoManagerFactory.getTodoManager(fileName);

        return switch (command) {
            case "insert" -> processInsertCommand(positionalArgs, cmd, todoManager, fileName);
            case "list" -> processListCommand(cmd, todoManager, fileName);
            case "migrate" -> processMigrateCommand(cmd, fileName);
            default -> {
                System.err.println("Commande non reconnue.");
                yield 1;
            }
        };
    }

    private static int processInsertCommand(List<String> positionalArgs, CommandLine cmd, TodoManager todoManager, String fileName) throws IOException {
        boolean isDone = cmd.hasOption("done");
        String description = String.join(" ", positionalArgs.subList(1, positionalArgs.size()));
        Todo todo = new Todo(description, isDone);

        todoManager.insertTodo(fileName, todo);
        System.err.println("Insertion réussie.");
        return 0;
    }

    private static int processListCommand(CommandLine cmd, TodoManager todoManager, String fileName) throws IOException {
        boolean showDone = cmd.hasOption("done");
        todoManager.listTodos(fileName, showDone);
        System.err.println("Liste générée.");
        return 0;
    }

    private static int processMigrateCommand(CommandLine cmd, String fileName) {
        String outputFileName = cmd.getOptionValue("output");
        if (outputFileName == null) {
            System.err.println("Option --output manquante");
            return 1;
        }

        MigrateCommand migrateCommand = new MigrateCommand();
        migrateCommand.migrate(fileName, outputFileName);
        System.err.println("Migration réussie.");
        return 0;
    }
}
