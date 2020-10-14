package ui;

import model.Task;
import model.ToDoList;

import java.util.*;

public class ToDoApp {
    private ToDoList tdl;
    private Task t1;
    private String s1;
    private String s2;
    private boolean boo1;
    private boolean boo2;
    private boolean boo3;
    private boolean boo4;
    private boolean boo5;
    private boolean boo6;
    private boolean boo7;
    private boolean found;
    private boolean found2;

    Scanner in = new Scanner(System.in);

    public ToDoApp() {
        runToDoList();
    }


    private void runToDoList() {
        boo2 = false;
        init();
        askDescriptionAndCompletion();
        createAndAdd(s1, boo1);
        while (boo2 == false) {
            intersection();
        }
    }


    private void init() {
        tdl = new ToDoList();
    }


    private void askDescriptionAndCompletion() {
        System.out.println("Type description of task");
        s1 = in.next();
        boo6 = false;
        while (boo6 == false) {
            System.out.println("Is the task complete? Type y for yes, and n for no.");
            String c1 = in.next();
            if (c1.equalsIgnoreCase("y")) {
                boo1 = true;
                boo6 = true;
            } else if (c1.equalsIgnoreCase("n")) {
                boo1 = false;
                boo6 = true;

            } else {
                System.out.println("Invalid input. Please try again.");
                boo6 = false;
            }
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
        System.out.println("Type r to remove task, a to add another task.");
        System.out.println("Type c to change completion, v to view lists, q to quit.");
        String s1 = in.next();
        if (s1.equalsIgnoreCase("a")) {
            addTask();
        }
        if (s1.equalsIgnoreCase("r")) {
            removeTask();
        }
        if (s1.equalsIgnoreCase("c")) {
            changeCompletion();
        }
        if (s1.equalsIgnoreCase("v")) {
            printAllTasks();
            printSummary();
        }
        if (s1.equalsIgnoreCase("q")) {
            boo2 = true;
            printAllTasks();
            printSummary();
        }
    }

    private void addTask() {
        boo3 = false;
        while (boo3 == false) {
            askDescriptionAndCompletion();
            createAndAdd(s1, boo1);
            System.out.println("Do you want to add another task? Type y for yes, n for no.");
            String s1 = in.next();
            if (s1.equalsIgnoreCase("y")) {
                boo3 = false;
            } else {
                boo3 = true;
            }
        }
    }

    private void changeHelper() {
        boo5 = false;
        found2 = false;
        System.out.println("Please type the name of the task who's completion you want changed.");
        s2 = in.next();
        for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
            if ((tdl.getListOfUncompletedTasks().get(i).getTask()).equalsIgnoreCase(s2)) {
                boo5 = true;
                found2 = true;
                tdl.addTask(new Task(s2, true));
                tdl.removeTask(tdl.getListOfUncompletedTasks().get(i));
            }
        }
    }

    private void changeHelper2() {
        found = false;
        {
            for (int i = 0; i < tdl.getListOfCompletedTasks().size(); i++) {
                if ((tdl.getListOfCompletedTasks().get(i).getTask()).equalsIgnoreCase(s2)) {
                    tdl.addTask(new Task(s2, false));
                    tdl.removeTask(tdl.getListOfCompletedTasks().get(i));
                    found = true;
                }
            }
            if (found == true) {
                System.out.println(s2 + " is now set to NOT completed" + '\n');
            } else {
                System.out.println("The task " + s2 + " was not found");
            }
        }
    }

    private void changeCompletion() {
        boo7 = false;
        while (boo7 == false) {
            changeHelper();
            if (boo5 == false) {
                changeHelper2();
            } else {
                if (found2 == true) {
                    System.out.println(s2 + " is now set to COMPLETED" + '\n');
                } else {
                    System.out.println("The task " + s2 + " was not found");
                }
            }
            System.out.println("Do you want to toggle another task? Type y for yes, n for no.");
            String s1 = in.next();
            if (s1.equalsIgnoreCase("y")) {
                boo7 = false;
            } else {
                boo7 = true;
            }
        }
    }

    private void removeTask() {
        boo4 = false;
        while (boo4 == false) {
            System.out.println("Please type the name of the task that you want removed");
            String ss = in.next();
            for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
                if ((tdl.getListOfUncompletedTasks().get(i).getTask()).equalsIgnoreCase(ss)) {
                    tdl.removeTask(tdl.getListOfUncompletedTasks().get(i));
                }
            }
            for (int i = 0; i < tdl.getListOfCompletedTasks().size(); i++) {
                if ((tdl.getListOfCompletedTasks().get(i).getTask()).equalsIgnoreCase(ss)) {
                    tdl.removeTask(tdl.getListOfCompletedTasks().get(i));
                }
            }
            System.out.println("Do you want to remove another task? Type y for yes, n for no.");
            String s1 = in.next();
            if (s1.equalsIgnoreCase("y")) {
                boo4 = false;
            } else {
                boo4 = true;
            }
        }
    }

}
