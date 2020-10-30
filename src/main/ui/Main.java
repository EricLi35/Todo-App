package ui;

import model.Task;
import model.ToDoList;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;

public class Main extends ToDoList {
    public static void main(String[] args) {
        try {
            new ToDoApp();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }
    }
}
