package persistence;

import model.ToDoList;
import model.Task;
import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class JsonReader {
    private String source;


    public JsonReader(String source) {
        this.source = source;
    }


    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList tdl = new ToDoList();
        addIncompleteTasks(tdl, jsonObject);
        addCompleteTasks(tdl, jsonObject);
        return tdl;
    }

    private void addIncompleteTasks(ToDoList tdl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Incomplete tasks");
        for (Object json : jsonArray) {
            String nextTask = json.toString();
            tdl.addTask(new Task(nextTask, false));
        }
    }

    private void addCompleteTasks(ToDoList tdl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Completed tasks");
        for (Object json : jsonArray) {
            String nextTask = json.toString();
            tdl.addTask(new Task(nextTask, true));
        }
    }


}
