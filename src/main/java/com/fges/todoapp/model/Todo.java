package com.fges.todoapp.model;

// Modèle de données pour un todo_
public class Todo {
    private String name;

    /* Constructeur qui initialise le nom du todos avec la valeur passée en paramètre*/
    public Todo(String todoName) {
        this.name = todoName;
    }

    // Getter pour obtenir le nom du todo_
    public String getName() {
        return name;
    }

    // Si nécessaire, vous pouvez également ajouter un setter pour le nom
    public void setName(String name) {
        this.name = name;
    }
}
