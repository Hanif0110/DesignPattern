package com.fges.todoapp;

// Importation des bibliothèques nécessaires pour le traitement JSON, l'analyse des arguments de ligne de commande, et la manipulation des fichiers.
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

// Définition de la couche de présentation, gérant les interactions initiales avec l'utilisateur.
public class App {
    public static void main(String[] args) throws Exception {
        // Point d'entrée de l'application, termine avec un code de sortie basé sur le succès ou l'échec de l'exécution.
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        // Configuration et parsing des options de ligne de commande.
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

        // Récupération du nom du fichier à partir des arguments et vérification de la commande spécifiée par l'utilisateur.
        String fileName = cmd.getOptionValue("s");
        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        // Vérifie si le fichier spécifié existe et, si oui, lit son contenu.
        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        // Instancie la couche logique pour exécuter la commande spécifiée.
        TodoService todoService = new TodoService(filePath);

        // Exécute la commande basée sur l'entrée de l'utilisateur et renvoie un code de sortie.
        switch (command) {
            case "insert":
                return todoService.insertTodo(positionalArgs);
            case "list":
                return todoService.listTodos();
            default:
                System.err.println("Unknown command");
                return 1;
        }
    }
}

// Définition de la couche logique, gérant la logique métier de l'application.
class TodoService {
    private Path filePath;

    public TodoService(Path filePath) {
        // Constructeur qui initialise le chemin du fichier utilisé pour stocker les todos.
        this.filePath = filePath;
    }

    public int insertTodo(List<String> positionalArgs) throws IOException {
        // Gère l'insertion d'un nouveau todo, en vérifiant d'abord si le nom du todo est spécifié.
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return 1;
        }
        String todo = positionalArgs.get(1);
        TodoDataAccess dao = new TodoDataAccess(filePath);
        dao.insertTodo(todo);
        System.err.println("Done.");
        return 0;
    }

    public int listTodos() throws IOException {
        // Gère l'affichage de tous les todos enregistrés.
        TodoDataAccess dao = new TodoDataAccess(filePath);
        dao.listTodos();
        System.err.println("Done.");
        return 0;
    }
}

// Définition de la couche de données, responsable de la lecture et de l'écriture des données.
class TodoDataAccess {
    private Path filePath;

    public TodoDataAccess(Path filePath) {
        // Constructeur qui initialise le chemin du fichier pour l'accès aux données.
        this.filePath = filePath;
    }

    public void insertTodo(String todo) throws IOException {
        String fileContent = new String(Files.readAllBytes(filePath));
        if (filePath.toString().endsWith(".json")) {
            JsonNode actualObj = readJson(fileContent);
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.add(todo);
            }
            writeJson(filePath, actualObj);
        } else if (filePath.toString().endsWith(".csv")) {
            List<String> lines = readCsv(fileContent);
            lines.add(todo);
            writeCsv(filePath, lines);
        }
    }

    public void listTodos() throws IOException {
        String fileContent = new String(Files.readAllBytes(filePath));
        if (filePath.toString().endsWith(".json")) {
            JsonNode actualObj = readJson(fileContent);
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> System.out.println("- " + node.asText()));
            }
        } else if (filePath.toString().endsWith(".csv")) {
            List<String> lines = readCsv(fileContent);
            lines.forEach(line -> System.out.println("- " + line));
        }
    }

    private JsonNode readJson(String fileContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }
        return actualObj;
    }

    private List<String> readCsv(String fileContent) {
        return Arrays.asList(fileContent.split("\n"));
    }

    private void writeJson(Path filePath, JsonNode data) throws IOException {
        Files.writeString(filePath, data.toString());
    }

    private void writeCsv(Path filePath, List<String> data) throws IOException {
        String csvContent = String.join("\n", data);
        Files.writeString(filePath, csvContent);
    }
}