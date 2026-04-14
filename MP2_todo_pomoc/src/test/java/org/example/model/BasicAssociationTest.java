package org.example.model;

import org.example.model.basic.Department;
import org.example.model.basic.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAssociationTest {
    Employee e1;
    Department d1;
    Department d2;

    @BeforeEach
    void setup() {
        e1 = new Employee("Kowalski");
        d1 = new Department("Sales");
        d2 = new Department("HR");
    }



    @Test
    void setDeptTest() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn(), "The Department object relation is not present in the Employee object");
        assertTrue(d1.getEmployees().contains(e1), "The Employee object relation is not present in the Department object");
    }

    @Test
    void addEmpTest() {
        //should not allow to add relation with a null object
        assertThrows(IllegalArgumentException.class, () -> d1.addEmployee(null), "You should not allow to create an association with null");
        d1.addEmployee(e1);
        assertEquals(d1, e1.getWorksIn(), "The Department object relation is not present in the Employee object");
        assertTrue(d1.getEmployees().contains(e1), "The Employee object relation is not present in the Department object");
    }

    @Test
    void addAndRemoveFromEmp() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn(), "The Department object relation is not present in the Employee object");
        assertTrue(d1.getEmployees().contains(e1), "The Employee object relation is not present in the Department object");

        e1.setWorksIn(null);
        assertNull(e1.getWorksIn(), "After removing Department from Employee, the latter should have null ref to Department");
        assertFalse(d1.getEmployees().contains(e1), "After removing Department from Employee, the former should not have reference to the removed Employee");

    }

    @Test
    void addAndRemoveFromDept() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn(), "The Department object relation is not present in the Employee object");
        assertTrue(d1.getEmployees().contains(e1), "The Employee object relation is not present in the Department object");

        d1.removeEmployee(e1);
        assertNull(e1.getWorksIn(), "After removing Employee from Department, the former should have null ref to Department");
        assertFalse(d1.getEmployees().contains(e1), "After removing Employee from Department, the latter should not have reference to the removed Employee");

    }
    @Test
    void testReplaceDepartment() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn(), "The Department object relation is not present in the Employee object");
        assertTrue(d1.getEmployees().contains(e1), "The Employee object relation is not present in the Department object");

        e1.setWorksIn(d2);

        //first relation should be removed
        assertFalse(d1.getEmployees().contains(e1), "After setting a new Department for an Employee, the Employee should be removed from the former Department ");

        //secondary relation should be set
        assertEquals(d2, e1.getWorksIn(), "The new Department object relation is not present in the Employee object");
        assertTrue(d2.getEmployees().contains(e1), "The Employee object relation is not present in the new Department object");
    }
}