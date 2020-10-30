package persistence;

import model.ToDoList;
import model.Task;
import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

// Represents a reader that reads todolist from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads todolist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses todolist from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList tdl = new ToDoList();
        addIncompleteTasks(tdl, jsonObject);
        addCompleteTasks(tdl, jsonObject);
        return tdl;
    }

    // MODIFIES: tdl
    // EFFECTS: parses tasks from JSON object and adds them to todolist's incomplete tasks section
    private void addIncompleteTasks(ToDoList tdl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Incomplete tasks");
        for (Object json : jsonArray) {
            String nextTask = json.toString();
            tdl.addTask(new Task(nextTask, false));
        }
    }

    // MODIFIES: tdl
    // EFFECTS: parses tasks from JSON object and adds them to todolist's complete tasks section
    private void addCompleteTasks(ToDoList tdl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Completed tasks");
        for (Object json : jsonArray) {
            String nextTask = json.toString();
            tdl.addTask(new Task(nextTask, true));
        }
    }


}
