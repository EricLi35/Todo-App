package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList tdl = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyTodolist() {
        try {
            ToDoList tdl = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTodoList.json");
            writer.open();
            writer.write(tdl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTodoList.json");
            tdl = reader.read();
            assertEquals(0 , tdl.getNumberOfIncompleteTasks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTodolist() {
        try {
            ToDoList tdl = new ToDoList();
            tdl.addTask(new Task("Math" , false));
            tdl.addTask(new Task("Stats" , true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTodoList.json");
            writer.open();
            writer.write(tdl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTodoList.json");
            tdl = reader.read();
            List<Task> completedList = tdl.getListOfCompletedTasks();
            assertEquals(1, completedList.size());
            assertEquals("Stats",completedList.get(0).getTask());
            List<Task> uncompletedList = tdl.getListOfUncompletedTasks();
            assertEquals(1, uncompletedList.size());
            assertEquals("Math",uncompletedList.get(0).getTask());

            checkTask("Stats",true, completedList.get(0));
            checkTask("Math",false, uncompletedList.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




}
