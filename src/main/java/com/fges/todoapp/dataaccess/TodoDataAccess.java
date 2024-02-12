package com.fges.todoapp.dataaccess;

import com.fges.todoapp.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// Classe pour accéder aux données des todos
public class TodoDataAccess {
    private Path filePath;

    public TodoDataAccess(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public void insertTodo(String todo) throws IOException {
        // Implémentation de l'insertion d'un todo
        // Exemple: Ajouter un todo au format JSON
    }

    public void listTodos() throws IOException {
        // Implémentation pour lister tous les todos
        // Exemple: Lire et afficher les todos depuis un fichier JSON
    }
}
