package org.example.model.composition;

import java.util.HashSet;
import java.util.Set;

public class Task {

    private static Set<Task> extent = new HashSet<>();
    private String description;

    private Project owner;

    public Task(String description, Project owner) {
        this.description = description;
        this.owner = owner;
        extent.add(this);
    }



    public Project getOwner() {
        return owner;
    }

    public static Set<Task> getExtent() {
        return new HashSet<>(extent);
    }

    //for tests only
    public static void resetExtent() {
        extent.clear();
    }
}
