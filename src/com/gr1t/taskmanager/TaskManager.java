package com.gr1t.taskmanager;

import java.util.*;

public class TaskManager {
    private static List<String> tasks = new ArrayList<>();

    public static Task createTask(String title, String description, Priority priority) {
        Task task = new Task(title, description, priority);
        tasks.add(String.valueOf(task));
        return task;
}

    public static void  printAllTasks() {
        for (String task : tasks) {
            System.out.println(task);
        }
    }
}
