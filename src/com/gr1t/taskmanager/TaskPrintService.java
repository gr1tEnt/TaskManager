package com.gr1t.taskmanager;

import java.util.List;

public class TaskPrintService {

    public static void printAllTasks(List<Task> tasks) {
        int number = 1;
        for (Task task : tasks) {
            System.out.println(number + " " + task);
            number++;
        }
    }

    public static void printTaskFound(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Found tasks: " + tasks);
        }
    }
}
