package org.example.model.basic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Department {
    private String name;

    private Set<Employee> employees = new HashSet<>();

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(Employee e) {
        this.employees.add(e);
    }

    public void removeEmployee(Employee e) {
        this.employees.remove(e);
    }

    public Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }
}

