package model;

import java.util.ArrayList;                 // reformat code: ctrl+alt+l

// Represents 2 lists, with 1 of them storing completed tasks, and the other storing uncompleted tasks
public class ToDoList {

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
     * EFFECTS: task t1 is added to toDoListComplete if it's a completed task,
     * otherwise task t1 is added to toDoListIncomplete.
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
     * MODIFIES: Task t1
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


}
