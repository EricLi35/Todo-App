package model;

public class Task {
    private String task;           // name or description of the task
    private boolean completion;    // represents whether a task is completed or not


    /*
     * REQUIRES: description has a non-zero length
     * EFFECTS: name of task is set to description, and task's completion is set to boo1.
     */
    public Task(String description, boolean boo1) {
        task = description;
        completion = boo1;
    }

    public String getTask() {
        return task;
    }

    public boolean getCompletion() {
        return completion;
    }

    public void setCompletion(boolean toggle) {
        completion = toggle;
    }


}
