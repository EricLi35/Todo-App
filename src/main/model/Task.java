package model;


// represents a "task". Each task has 2 characteristics composed of the actual
// description which is a string, and a boolean that represents whether the task is completed or not.
// Tasks can be anything that needs to be completed, and it ranges from washing dishes to vacuuming, etc.
public class Task {
    private String task;           // name or description of the task
    private boolean completion;    // represents whether a task is completed or not


    /*
     * REQUIRES: description has a non-zero length
     * EFFECTS: constructs a task whose name is set to description, and completion is set to boo1.
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
