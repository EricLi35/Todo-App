package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    protected void checkTask(String s, boolean boo, Task t) {
        assertEquals(s, t.getTask());
        assertEquals(boo, t.getCompletion());
    }


}
