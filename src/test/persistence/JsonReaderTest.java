package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList tdl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTodoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTodoList.json");

        try {
            ToDoList tdl = reader.read();
            assertEquals(0, tdl.getNumberOfCompleteTasks());
            assertEquals(0, tdl.getNumberOfIncompleteTasks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTodoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTodoList.json");
        try {
            ToDoList tdl = reader.read();
            List<Task> ct = tdl.getListOfCompletedTasks();
            List<Task> uct = tdl.getListOfUncompletedTasks();
            assertEquals("Stats" , ct.get(0).getTask());
            assertEquals("Math" , uct.get(0).getTask());
            assertEquals(1, tdl.getNumberOfIncompleteTasks());
            assertEquals(1, tdl.getNumberOfCompleteTasks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
