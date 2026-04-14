package org.example.model.basic;

public class Employee {

    private String lastName;
    private Department worksIn;

    public Employee(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getWorksIn() {
        return worksIn;
    }

    public void setWorksIn(Department worksIn) {
        if(this.worksIn == worksIn) {
            return;
        }
        this.worksIn = worksIn;
//        if(newAssociation) {
//
//        }
//        if(deleteAssociation) {
//
//        }
//        if(changeRelation) {
//
//        }


    }
}
