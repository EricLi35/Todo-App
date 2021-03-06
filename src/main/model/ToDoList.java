package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Represents 2 lists, with 1 of them storing completed tasks, and the other storing uncompleted tasks
public class ToDoList implements Writable {

    private ArrayList<Task> toDoListComplete;           // a list of all the tasks that are completed
    private ArrayList<Task> toDoListIncomplete;         // a list of all the tasks that are uncompleted


    /*
     * EFFECTS: sets both toDoListIncomplete and toDoListComplete to new ArrayLists.
     */
    public ToDoList() {
        toDoListComplete = new ArrayList<>();
        toDoListIncomplete = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: first checks to see if the task name is already in the complete or incomplete lists.
     * if it is, throw new TaskAlreadyExistsException.
     * Otherwise, task is added to toDoListComplete if it's a completed task, or toDoListIncomplete if it's incomplete
     */
    public void addTask(Task t1) {

        if (t1.getCompletion() == true) {
            toDoListComplete.add(t1);
        }
        if (t1.getCompletion() == false) {
            toDoListIncomplete.add(t1);
        }
    }

    /*
     * REQUIRES: size of either list >= 1
     * MODIFIES: this
     * EFFECTS: task t1 is removed from toDoListComplete if t1 is within toDoListComplete,
     * otherwise t1 is removed from toDoListIncomplete.
     */
    public void removeTask(Task t1) {
        if (toDoListComplete.contains(t1)) {
            toDoListComplete.remove(t1);
        } else {
            toDoListIncomplete.remove(t1);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: reverses whatever the boolean variable completion is set to,
     * and adds task onto completed list if task was originally
     * incomplete, and vice versa.
     */
    public void toggleCompletion(Task t1) {
        if (t1.getCompletion() == true) {
            t1.setCompletion(false);
            toDoListIncomplete.add(t1);
            toDoListComplete.remove(t1);
        } else {
            t1.setCompletion(true);
            toDoListComplete.add(t1);
            toDoListIncomplete.remove(t1);
        }
    }


    public ArrayList<Task> getListOfCompletedTasks() {
        return toDoListComplete;
    }

    public ArrayList<Task> getListOfUncompletedTasks() {
        return toDoListIncomplete;
    }

    public int getNumberOfCompleteTasks() {
        return toDoListComplete.size();
    }

    public int getNumberOfIncompleteTasks() {
        return toDoListIncomplete.size();
    }


    @Override
    // MODIFIES: json
    // EFFECTS: writes the 2 types of task to file
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Incomplete tasks", incompleteTasksToJson());
        json.put("Completed tasks", completeTasksToJson());
        return json;
    }

    // MODIFIES: jsonArray
    // EFFECTS: puts list of list of incomplete tasks into jsonArray
    private JSONArray incompleteTasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < toDoListIncomplete.size(); i++) {
            jsonArray.put(toDoListIncomplete.get(i).getTask());
        }
        return jsonArray;
    }

    // MODIFIES: jsonArray
    // EFFECTS: puts list of list of complete tasks into jsonArray
    private JSONArray completeTasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < toDoListComplete.size(); i++) {
            jsonArray.put(toDoListComplete.get(i).getTask());
        }
        return jsonArray;
    }


}
