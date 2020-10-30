package persistence;

import model.ToDoList;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private String destination;
    private PrintWriter writer;


    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(ToDoList tdl) {
        JSONObject json = tdl.toJson();
        saveToFile(json.toString(4));
    }

    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

}
