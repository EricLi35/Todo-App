package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ToDoApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private ToDoList tdl;
    private Task t1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private boolean boo1;
    private boolean boo2;
    private boolean boo3;
    private boolean boo4;
    private boolean boo5;
    private boolean boo6;
    private boolean boo7;
    private boolean boo8;
    private boolean boo9;

    Scanner in = new Scanner(System.in);

    public ToDoApp() throws FileNotFoundException {
        tdl = new ToDoList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runToDoList();
    }


    private void runToDoList() {
        boo2 = false;
//        askDescriptionAndCompletion();
//        createAndAdd(s1, boo1);
        while (boo2 == false) {
            intersection();
        }
    }


    private void askDescriptionAndCompletion() {
        System.out.println("Type description of task");
        boo6 = false;

        while (boo6 == false) {
            s1 = in.nextLine();
            if (s1.length() != 0) {
                boo6 = true;
            }
        }

        System.out.println("Is \"" + s1 + "\" complete? Type y for yes, and n for no.");
        s6 = in.next();
        if (s6.equalsIgnoreCase("y")) {
            boo1 = true;
        } else if (s6.equalsIgnoreCase("n")) {
            boo1 = false;
        }
    }

    private void createAndAdd(String s1, boolean boo1) {
        t1 = new Task(s1, boo1);
        tdl.addTask(t1);
    }

    private void printSummary() {
//        System.out.println("The description of your task is " + t1.getTask());
//        System.out.println("Status of completion: " + t1.getCompletion());
        System.out.println('\n' + "SIZE OF COMPLETED TASK LIST: " + tdl.getNumberOfCompleteTasks());
        System.out.println("SIZE OF UNCOMPLETED TASK LIST: " + tdl.getNumberOfIncompleteTasks() + '\n');
    }

    private void printAllTasks() {
        tdl.getListOfCompletedTasks();
        System.out.println('\n' + "LIST OF COMPLETED TASKS");
        for (int i = 0; i < tdl.getListOfCompletedTasks().size(); i++) {
            System.out.println(tdl.getListOfCompletedTasks().get(i).getTask());
        }
        tdl.getListOfUncompletedTasks();
        System.out.println('\n' + "LIST OF INCOMPLETE TASKS");
        for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
            System.out.println(tdl.getListOfUncompletedTasks().get(i).getTask());
        }
    }

    private void intersection() {
        System.out.println("Type r to remove task, a to add another task, s to save json & l to load json.");
        System.out.println("Type c to change completion, v to view lists, q to quit.");
        s4 = in.next();
        processCommand(s4);
    }

    private void processCommand(String x) {
        if (x.equalsIgnoreCase("a")) {
            addTask();
        }
        if (x.equalsIgnoreCase("r")) {
            removeTask();
        }
        if (x.equalsIgnoreCase("c")) {
            changeCompletionMain();
        }
        if (x.equalsIgnoreCase("s")) {
            saveToDoList();
        }
        if (x.equalsIgnoreCase("l")) {
            loadWorkRoom();
        }
        if (x.equalsIgnoreCase("v")) {
            printAllTasks();
            printSummary();
        }
        if (x.equalsIgnoreCase("q")) {
            boo2 = true;
        }
    }

    private void addTask() {
        boo3 = false;

        while (boo3 == false) {
            askDescriptionAndCompletion();
            createAndAdd(s1, boo1);

            System.out.println("Do you want to add another task? Type y for yes, anything else means no.");
            s3 = in.next();
            if (s3.equalsIgnoreCase("y")) {
                boo3 = false;

            } else {
                boo3 = true;
            }
        }
    }


    private void changeCompletionMain() {
        System.out.println("Type name of task that you want its completion toggled");
        boo7 = false;
        boo8 = false;
        while (boo7 == false) {
            s2 = in.nextLine();
            if (s2.length() != 0) {
                boo7 = true;
            }
        }
        boo5 = false;
        changeCompletionIncomplete();
        changeCompletionComplete();
        if (boo8 == false) {
            System.out.println("The task " + '\'' + s2 + '\'' + " could not be found" + '\n');
        }
    }


    private void changeCompletionIncomplete() {
        for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
            if (tdl.getListOfUncompletedTasks().get(i).getTask().equalsIgnoreCase(s2)) {
                boo5 = true;
                boo8 = true;
                tdl.toggleCompletion(tdl.getListOfUncompletedTasks().get(i));
                System.out.println("The task " + '\'' + s2 + '\'' + " has been moved to completed" + '\n');
            }
        }
    }

    private void changeCompletionComplete() {
        if (boo5 == false) {
            for (int i = 0; i < tdl.getListOfCompletedTasks().size(); i++) {
                if (tdl.getListOfCompletedTasks().get(i).getTask().equalsIgnoreCase(s2)) {
                    tdl.toggleCompletion(tdl.getListOfCompletedTasks().get(i));
                    boo8 = true;
                    System.out.println("The task " + '\'' + s2 + '\'' + " has been moved to uncompleted" + '\n');
                }
            }
        }
    }

    private void removeTask() {
        boo9 = false;
        System.out.println("Please type the name of the task that you want removed");
        boo4 = false;
        while (boo4 == false) {
            s5 = in.nextLine();
            if (s5.length() != 0) {
                boo4 = true;
            }
        }
        removeTaskLoopUncompleted();
        removeTaskLoopCompleted();
        if (boo9 == true) {
            System.out.println("Successfully removed " + s5 + " from the todo list" + '\n');
        }
        if (boo9 == false) {
            System.out.println(s5 + " could not be found within the todo list" + '\n');
        }
    }

    private void removeTaskLoopUncompleted() {
        for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
            if ((tdl.getListOfUncompletedTasks().get(i).getTask()).equalsIgnoreCase(s5)) {
                tdl.removeTask(tdl.getListOfUncompletedTasks().get(i));
                boo9 = true;
            }
        }
    }

    private void removeTaskLoopCompleted() {
        for (int i = 0; i < tdl.getListOfCompletedTasks().size(); i++) {
            if ((tdl.getListOfCompletedTasks().get(i).getTask()).equalsIgnoreCase(s5)) {
                tdl.removeTask(tdl.getListOfCompletedTasks().get(i));
                boo9 = true;
            }
        }
    }

    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(tdl);
            jsonWriter.close();
            System.out.println("Saved todo list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadWorkRoom() {
        try {
            tdl = jsonReader.read();
            System.out.println("Successfully loaded todo list from Json file" + '\n');
        } catch (IOException e) {
            System.out.println("Unable to read from the file " + JSON_STORE);
        }
    }
}
