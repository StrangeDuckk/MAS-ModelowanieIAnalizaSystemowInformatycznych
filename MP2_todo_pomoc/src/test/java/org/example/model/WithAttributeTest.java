package org.example.model;

import org.example.model.withAttribute.Employee;
import org.example.model.withAttribute.Partipaction;
import org.example.model.withAttribute.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class WithAttributeTest {

    Employee e1;
    Employee e2;
    Project p1;
    Project p2;

    @BeforeEach
    void setup() {
        e1 = new Employee("Kowalski");
        e2 = new Employee("Nowak");
        p1 = new Project("Project 1");
        p2 = new Project("Project 2");
    }

    @Test
    void createSuccessfully() {
        assertThrows(IllegalArgumentException.class,
                () -> { new Partipaction(null, p1, LocalDate.now()); },
                "you should not allow to create a association object with null employee");
        assertThrows(IllegalArgumentException.class,
                () -> { new Partipaction(e1, null, LocalDate.now()); },
                "you should not allow to create a association object with null project");


        Partipaction part1 = new Partipaction(e1, p1, LocalDate.now());

        //all references should be set
        assertEquals(e1, part1.getEmployee());
        assertEquals(p1, part1.getProject());
        assertTrue(e1.getPartipactions().contains(part1));
        assertTrue(p1.getPartipactions().contains(part1));

        //attempt to use add this participation to unrelated Project
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                p2.addParticipation(part1);
            }
        );

        //attempt to use add this participation to unrelated employee
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                e2.addParticipation(part1);
            }
        );

        //for normal association (not bag or history)
        // we should not be able to make duplicate relation
        assertThrows(IllegalArgumentException.class,
            () -> { new Partipaction(e1, p1, LocalDate.now()); }
        );

        e1.removeParticipation(part1);
        //now all 4 references should be removed
        assertTrue(part1.getEmployee() == null);
        assertTrue(part1.getProject() == null);
        assertFalse(e1.getPartipactions().contains(part1));
        assertFalse(p1.getPartipactions().contains(part1));
    }




}
