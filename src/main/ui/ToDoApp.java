package ui;

import model.Task;
import model.ToDoList;

import java.util.*;

public class ToDoApp {
    private ToDoList tdl;
    private Task t1;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private boolean boo1;
    private boolean boo2;
    private boolean boo3;
    private boolean boo4;
    private boolean boo5;
    private boolean boo6;
    private boolean boo7;
    private boolean boo8;
    private boolean boo9;
    private boolean boo10;

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
        boo7 = false;

        while (boo7 == false) {
            s1 = in.nextLine();
            if (s1.length() != 0) {
                boo7 = true;
            }
        }
        boo6 = false;
        while (boo6 == false) {
            System.out.println("Is \"" + s1 + "\" complete? Type y for yes, and n for no.");
            String c1 = in.next();
            if (c1.equalsIgnoreCase("y")) {
                boo1 = true;
                boo6 = true;
            } else if (c1.equalsIgnoreCase("n")) {
                boo1 = false;
                boo6 = true;

            } else {
                System.out.println('\n' + "Invalid input. Please try again.");
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
        s4 = in.next();
        if (s4.equalsIgnoreCase("a")) {
            addTask();
        }
        if (s4.equalsIgnoreCase("r")) {
            removeTask();
        }
        if (s4.equalsIgnoreCase("c")) {
            changeCompletionMain();
        }
        if (s4.equalsIgnoreCase("v")) {
            printAllTasks();
            printSummary();
        }
        if (s4.equalsIgnoreCase("q")) {
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
            boo9 = false;

            while (boo9 == false) {
                System.out.println("Do you want to add another task? Type y for yes, n for no.");
                s3 = in.next();

                if (s3.equalsIgnoreCase("y")) {
                    boo3 = false;
                    boo9 = true;
                } else if (s3.equalsIgnoreCase("n")) {
                    boo3 = true;
                    boo9 = true;
                } else {
                    System.out.println('\n' + "Invalid input. Please try again");
                }
            }

        }
    }


    private void changeCompletionMain() {
        System.out.println("Type name of task that you want its completion toggled");
        boo8 = false;
        boo10 = false;
        while (boo8 == false) {
            s2 = in.nextLine();
            if (s2.length() != 0) {
                boo8 = true;
            }
        }
        boo5 = false;
        changeCompletionIncomplete();
        changeCompletionComplete();
        if (boo10 == false) {
            System.out.println("The task " + '\'' + s2 + '\'' + " could not be found" + '\n');
        }
    }


    private void changeCompletionIncomplete() {
        for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
            if (tdl.getListOfUncompletedTasks().get(i).getTask().equalsIgnoreCase(s2)) {
                boo5 = true;
                boo10 = true;
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
                    boo10 = true;
                    System.out.println("The task " + '\'' + s2 + '\'' + " has been moved to uncompleted" + '\n');
                }
            }
        }
    }


    private void removeTask() {
        System.out.println("Please type the name of the task that you want removed");
        boo4 = false;
        while (boo4 == false) {
            s5 = in.nextLine();
            if (s5.length() != 0) {
                boo4 = true;
            }
        }
        for (int i = 0; i < tdl.getListOfUncompletedTasks().size(); i++) {
            if ((tdl.getListOfUncompletedTasks().get(i).getTask()).equalsIgnoreCase(s5)) {
                tdl.removeTask(tdl.getListOfUncompletedTasks().get(i));
            }
        }
        for (int i = 0; i < tdl.getListOfCompletedTasks().size(); i++) {
            if ((tdl.getListOfCompletedTasks().get(i).getTask()).equalsIgnoreCase(s5)) {
                tdl.removeTask(tdl.getListOfCompletedTasks().get(i));
            }
        }
        System.out.println(s5 + " has been removed from the todo list" + '\n');
    }
}
