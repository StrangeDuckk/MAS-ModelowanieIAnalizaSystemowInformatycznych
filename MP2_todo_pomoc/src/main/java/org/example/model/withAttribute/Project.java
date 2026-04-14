package org.example.model.withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Project {
    private String name;

    private Set<Partipaction> partipactions = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Partipaction> getPartipactions() {
        return new HashSet<>(partipactions);
    }

    public void addParticipation(Partipaction p) {

    }

    public void removeParticipation(Partipaction p) {

    }
}
