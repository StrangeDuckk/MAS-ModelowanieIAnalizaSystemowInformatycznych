package org.example.model.qualified;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Company {
    private String name;

    //name of the department is a qualifier here
    private Map<String, Department> departments = new HashMap<>();


    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Department> getDepartments() {
        return new HashMap<>(departments);
    }

    public Department getDepartmentByName(String name) {
        return null;
    }

    public void addDepartment(Department d) {

    }

    public void removeDepartment(Department d) {

    }
}
