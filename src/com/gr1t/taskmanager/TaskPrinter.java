package com.gr1t.taskmanager;

import java.util.List;

public class TaskPrinter {

    public static void printAllTasks(List<Task> tasks) {
        int number = 1;
        for (Task task : tasks) {
            System.out.println(number + " " + task);
            number++;
        }
    }
}
