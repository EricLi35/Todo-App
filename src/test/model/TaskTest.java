package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @Test
    void testGetTask(){
        task = new Task("math" , false);
        assertEquals("math", task.getTask());
    }

    @Test
    void testGetCompletion(){
        task = new Task("math" , false);
        assertFalse(task.getCompletion());
    }

    @Test
    void testSetCompletion(){
        task = new Task("math" , false);
        assertFalse(task.getCompletion());
        task.setCompletion(true);
        assertTrue(task.getCompletion());
    }

}
