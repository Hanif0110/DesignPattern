package com.fges.todoapp.lecture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoReaderJson implements TodoReader {

    private final ObjectMapper mapper;

    public TodoReaderJson() {
        mapper = new ObjectMapper();
    }

    @Override
    public List<Todo> readTodos(String content) throws IOException {
        // Vérifie si le contenu n'est pas vide ou null avant de le traiter
        if (content == null || content.trim().isEmpty()) {
            return new ArrayList<>();
        }

        // Analyse le contenu JSON en utilisant Jackson
        JsonNode rootNode = mapper.readTree(content);

        // Initialise une nouvelle liste pour les todos
        List<Todo> todos = new ArrayList<>();

        // Vérifie si le noeud racine est un tableau JSON
        if (rootNode.isArray()) {
            // Convertit chaque noeud du tableau en un objet Todo et l'ajoute à la liste
            rootNode.forEach(node -> {
                Todo todo = mapper.convertValue(node, Todo.class);
                todos.add(todo);
            });
        } else {
            // Si le noeud racine n'est pas un tableau, renvoie une liste vide
            // Cela pourrait également être une exception si attendu autrement
            return new ArrayList<>();
        }

        return todos;
    }
}
