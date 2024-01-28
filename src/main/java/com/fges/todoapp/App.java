package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

// Commande d'insertion refactorisée en utilisant des méthodes de lecture et d'écriture séparées
        if (command.equals("insert")) {
            if (positionalArgs.size() < 2) {
                System.err.println("Missing TODO name");
                return 1;
            }
            String todo = positionalArgs.get(1);

            if (fileName.endsWith(".json")) {
                JsonNode actualObj = readJson(fileContent);
                if (actualObj instanceof ArrayNode arrayNode) {
                    arrayNode.add(todo);
                }
                writeJson(filePath, actualObj);
            } else if (fileName.endsWith(".csv")) {
                List<String> lines = readCsv(fileContent);
                lines.add(todo);
                writeCsv(filePath, lines);
            }
        }
// Commande de liste refactorisée en utilisant des méthodes de lecture séparées
        else if (command.equals("list")) {
            if (fileName.endsWith(".json")) {
                JsonNode actualObj = readJson(fileContent);
                if (actualObj instanceof ArrayNode arrayNode) {
                    arrayNode.forEach(node -> System.out.println("- " + node.asText()));
                }
            } else if (fileName.endsWith(".csv")) {
                List<String> lines = readCsv(fileContent);
                lines.forEach(line -> System.out.println("- " + line));
            }
        } else {
            System.err.println("Unknown command");
            return 1;
        }

        System.err.println("Done.");
        return 0;
    }

    // Méthode pour lire le contenu JSON à partir du fichier
    private static JsonNode readJson(String fileContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }
        return actualObj;
    }

    // Méthode pour lire le contenu CSV à partir du fichier
    private static List<String> readCsv(String fileContent) {
        return Arrays.asList(fileContent.split("\n"));
    }

    // Méthode pour écrire le contenu JSON dans le fichier
    private static void writeJson(Path filePath, JsonNode data) throws IOException {
        Files.writeString(filePath, data.toString());
    }

// Méthode pour écrire le contenu CSV dans le fichier
    private static void writeCsv(Path filePath, List<String> data) throws IOException {
        String csvContent = String.join("\n", data);
        Files.writeString(filePath, csvContent);
    }
}
