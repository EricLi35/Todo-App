package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList tdl;

    @Test
    void testToDoListConstructor() {
        tdl = new ToDoList();
        assertEquals(0, tdl.getNumberOfCompleteTasks());
        assertEquals(0, tdl.getNumberOfIncompleteTasks());
    }

    @Test
    void testAddTask() {
        tdl = new ToDoList();
        Task t1 = new Task("math", false);
        Task t2 = new Task("science", true);
        tdl.addTask(t1);
        tdl.addTask(t2);
        assertEquals(1, tdl.getNumberOfIncompleteTasks());
        assertEquals(1, tdl.getNumberOfCompleteTasks());
    }

    @Test
    void testRemoveCompleteTask(){
        tdl = new ToDoList();
        Task t1 = new Task("math", true);
        tdl.addTask(t1);
        assertEquals(1,tdl.getNumberOfCompleteTasks());
        tdl.removeTask(t1);
        assertEquals(0,tdl.getNumberOfCompleteTasks());

    }
    @Test
    void testRemoveIncompleteTask() {
        tdl = new ToDoList();
        Task t1 = new Task("math", false);
        Task t2 = new Task("science", false);
        tdl.addTask(t1);
        tdl.addTask(t2);
        assertEquals(2, tdl.getNumberOfIncompleteTasks());
        tdl.removeTask(t2);
        assertEquals(1, tdl.getNumberOfIncompleteTasks());
        tdl.getListOfUncompletedTasks().remove(t1);
        assertEquals(0,tdl.getNumberOfIncompleteTasks());
    }

    @Test
    void testToggleCompletion() {
        tdl = new ToDoList();
        Task t1 = new Task("math", false);
        tdl.addTask(t1);
        assertEquals(1, tdl.getNumberOfIncompleteTasks());
        assertFalse(t1.getCompletion());
        tdl.toggleCompletion(t1);
        assertTrue(t1.getCompletion());
        tdl.toggleCompletion(t1);
        assertFalse(t1.getCompletion());
    }

    @Test
    void testGetListOfCompletedTasks() {
        tdl = new ToDoList();
        Task t1 = new Task("math", true);
        tdl.addTask(t1);
        assertEquals("math", tdl.getListOfCompletedTasks().get(0).getTask());
    }

    @Test
    void testGetListOfUncompletedTasks() {
        tdl = new ToDoList();
        Task t1 = new Task("math", false);
        tdl.addTask(t1);
        assertEquals("math", tdl.getListOfUncompletedTasks().get(0).getTask());
    }



//    @Test
//    void testGetCompletedList(){
//        tdl = new ToDoList();
//        Task t1 = new Task("math",true);
//        tdl.addTask(t1);
//    }


}
