package org.example.model;

import org.example.model.composition.Project;
import org.example.model.composition.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositionTest {

    Project p1;
    Project p2;


    @BeforeEach
    void setup() {
        Task.resetExtent();
        p1 = new Project("Project 1");
        p2 = new Project("Project 2");
    }

    @Test
    void createTaskTest() {
        //you cannot create a task without a project
        assertThrows(IllegalArgumentException.class, () -> { new Task("desc", null); }, "It's shouldn't be allowed to create the 'part' without the 'whole'");
        Task t1 = new Task("desc", p1);
        assertEquals(p1, t1.getOwner(), "The 'part' has no reference to the 'whole'");
        assertTrue(p1.getTasks().contains(t1), "The 'whole' has no reference to the 'part'");
    }

    @Test
    void removeTaskFromProjectTest() {
        Task t1 = new Task("desc", p1);
        assertEquals(p1, t1.getOwner(), "The 'part' has no reference to the 'whole'");
        assertTrue(p1.getTasks().contains(t1), "The 'whole' has no reference to the 'part'");
        assertTrue(Task.getExtent().contains(t1), "After proper initialization the 'part' should be added to the class extent");

        //remove from project
        p1.removeTask(t1);
        assertNull(t1.getOwner(), "After removing the relation, the 'part' should have no reference to the 'whole'");
        assertFalse(p1.getTasks().contains(t1), "After removing the relation, the 'whole' should have no reference to the 'part'");

        //the task should not exist anymore
        assertFalse(Task.getExtent().contains(t1), "After removing the composed task, it should be removed from the class extent");
    }

    @Test
    void switchTaskOwner() {
        Task t1 = new Task("desc", p1);
        assertEquals(p1, t1.getOwner(), "The 'part' has no reference to the 'whole'");
        assertTrue(p1.getTasks().contains(t1), "The 'whole' has no reference to the 'part'");

        //it's not possible to change an owner of a composed object
        assertThrows(IllegalArgumentException.class,
            () -> {
                p2.addTask(t1);
            },
                "It's shouldn't be allowed to change the 'whole' for the 'part'"
        );
    }

}
