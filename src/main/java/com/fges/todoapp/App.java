package com.fges.todoapp;

import com.fges.todoapp.service.TodoService;
import org.apache.commons.cli.*;

// Classe principale pour gérer les interactions initiales avec l'utilisateur
public class App {
    public static void main(String[] args) {
        System.exit(exec(args));
    }

    public static int exec(String[] args) {
        // Configuration et parsing des options de ligne de commande
        Options options = new Options();
        options.addRequiredOption("s", "source", true, "File containing the todos");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        TodoService todoService = new TodoService(fileName);

        // Exécution de la commande spécifiée par l'utilisateur
        return todoService.executeCommand(cmd);
    }
}
