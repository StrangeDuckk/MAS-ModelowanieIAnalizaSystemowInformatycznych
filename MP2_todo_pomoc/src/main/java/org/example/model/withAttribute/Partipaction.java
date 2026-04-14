package org.example.model.withAttribute;

import java.time.LocalDate;

public class Partipaction {
    private Employee employee;
    private Project project;

    private LocalDate startAt;

    public Partipaction(Employee employee, Project project, LocalDate startAt) {
        this.employee = employee;
        this.project = project;
        this.startAt = startAt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    //its not necessary to change the side of this relation, so it can be private method
    //but if its public it needs to be implemented properly (for switching the employee)
    private void setEmployee(Employee employee) {
        //sprawdzenie czy nie null
        employee.addParticipation(this);
        this.employee = employee;
    }

    private void setProject(Project project) {
        this.project = project;
    }

    public void removeAllConnections(){
        //zmienne tymczasowe sa git tutaj do uzycia

        Employee tempemp = employee;
        employee = null;//todo rzeczywiscie znullowac te referencje
        tempemp.removeParticipation(this);

        //i tak samo dla projektu
        project = null;
    }
}
