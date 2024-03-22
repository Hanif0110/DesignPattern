package com.fges.todoapp.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Todo {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0); // Génère un ID unique
    private final int id;
    private String description;
    private boolean done;
    private LocalDate dueDate; // Ajoute une date d'échéance optionnelle

    // Le constructeur sans arguments n'est plus nécessaire si nous utilisons des constructeurs avec paramètres

    public Todo(String description, boolean done) {
        this.id = ID_GENERATOR.incrementAndGet(); // Assigner un nouvel ID
        this.description = description;
        this.done = done;
    }

    // Ajout d'un constructeur avec une date d'échéance
    public Todo(String description, boolean done, LocalDate dueDate) {
        this(description, done);
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    // Getter et Setter pour dueDate
    public Optional<LocalDate> getDueDate() {
        return Optional.ofNullable(dueDate);
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Surcharge de toString pour une représentation de l'objet plus utile en mode texte
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", dueDate=" + dueDate +
                '}';
    }
}
