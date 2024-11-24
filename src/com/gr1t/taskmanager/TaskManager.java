package com.gr1t.taskmanager;

import java.util.*;
import java.io.*;

public class TaskManager {
    private static List<Task> tasks = new ArrayList<>();

    public static Task createTask(String title, String description, Priority priority) {
        Task task = new Task(title, description, priority);
        tasks.add(task);
        return task;
}

    public static void  printAllTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public static void saveTasks (String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"));) {
            for (Task task : tasks) {
                writer.write(String.valueOf(task));
                writer.newLine();
            }
            System.out.println("File successfully saved as: " + fileName + ".txt");
        } catch (IOException e) {
            System.out.println("Your fault is: " + e.getMessage());
        }
    }

    public static void sortByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
    }
}
