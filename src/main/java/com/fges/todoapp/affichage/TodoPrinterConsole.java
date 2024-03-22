package com.fges.todoapp.affichage;

import com.fges.todoapp.model.Todo;

import java.util.List;

public class TodoPrinterConsole extends TodoPrinter {

    @Override
    public void printTodos(List<Todo> todos, boolean showDone) {
        todos.forEach(todo -> {
            // If showDone is true, print all todos. Otherwise, print only those not done.
            if (showDone || !todo.isDone()) {
                String status = todo.isDone() ? "Done: " : "";
                System.out.println("- " + status + todo.getDescription());
            }
        });
    }
}
