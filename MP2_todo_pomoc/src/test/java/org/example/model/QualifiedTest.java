package org.example.model;

import org.example.model.qualified.Company;
import org.example.model.qualified.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class QualifiedTest {

    Company c1;
    Department d1;

    @BeforeEach
    void setup() {
        c1 = new Company("ACME");
        d1 = new Department("Sales");
    }

    @Test
    void createTest() {
        c1.addDepartment(d1);
        assertTrue( c1.getDepartments().get(d1.getName()) == d1 );
        assertTrue( d1.getCompany() == c1);

        //check if getDeptByName works properly
        assertEquals(d1, c1.getDepartmentByName(d1.getName()));

    }


    @Test
    void createTest2() {
        d1.setCompany(c1);
        assertTrue( c1.getDepartments().get(d1.getName()) == d1 );
        assertTrue( d1.getCompany() == c1);

        //check if getDeptByName works properly
        assertEquals(d1, c1.getDepartmentByName(d1.getName()));

    }

    @Test
    void removeTest() {
        c1.addDepartment(d1);
        assertTrue( c1.getDepartments().get(d1.getName()) == d1 );
        assertTrue( d1.getCompany() == c1);

        c1.removeDepartment(d1);
        assertTrue(d1.getCompany() == null);
        assertFalse(c1.getDepartments().containsKey(d1.getName()));
    }

    @Test
    void removeTest2() {
        c1.addDepartment(d1);
        assertTrue( c1.getDepartments().get(d1.getName()) == d1 );
        assertTrue( d1.getCompany() == c1);

        d1.setCompany(null);
        assertTrue(d1.getCompany() == null);
        assertFalse(c1.getDepartments().containsKey(d1.getName()));
    }
}
