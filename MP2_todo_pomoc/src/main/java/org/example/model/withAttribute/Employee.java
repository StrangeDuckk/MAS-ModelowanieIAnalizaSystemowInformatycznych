package org.example.model.withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Employee {
    private String lastName;

    private Set<Partipaction> partipactions = new HashSet<>();

    public Employee(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Partipaction> getPartipactions() {
        return new HashSet<>(partipactions);
    }

    public void addParticipation(Partipaction p) {
        //przed przyjmowaniem sprawdzenie
        if(p != null && !partipactions.contains(p)){
            this.partipactions.add(p);
            //todo tak +- dodawanie relacji
        }
    }

    public void removeParticipation(Partipaction p) {

    }
}
