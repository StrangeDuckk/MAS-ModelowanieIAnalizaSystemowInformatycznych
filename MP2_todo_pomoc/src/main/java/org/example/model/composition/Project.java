package org.example.model.composition;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Project {
    private String name;

    private Set<Task> tasks = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    public void addTask(Task t1) {

    }

    public void removeTask(Task t1) {

    }
}
