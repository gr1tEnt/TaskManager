package com.gr1t.taskmanager;

import java.util.*;
import java.io.*;

public class TaskManager {
    private static final List<Task> tasks = new ArrayList<>();

    public static Task createTask(String title, String description, Priority priority) {
        Task task = new Task(title, description, priority);
        tasks.add(task);
        return task;
    }

    public static void printAllTasks() {
        int number = 1;
        for (Task task : tasks) {
            System.out.println(number + " " + task);
            number++;
        }
    }

    public static void saveTasks(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
            for (Task task : tasks) {
                writer.write(String.valueOf(task));
                writer.newLine();
            }
            System.out.println("File successfully saved as: " + fileName + ".txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
    }

    public static void removeTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
        } else {
            System.out.println("Invalid task number. Please try again");
        }
    }


    public static void sortByStatus() {
        tasks.sort(Comparator.comparing(Task::isCompleted));
    }

    public static void makeCompleted(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setCompleted(true);
            System.out.println("Task is completed: " + task);
        } else {
            System.out.println("Invalid task number. Please try again");
        }
    }

    public static void updateTask (int taskNum, String newTitle, String newDescription, Priority newPriority) {
        Task task = tasks.get(taskNum - 1);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        task.setPriority(newPriority);
        System.out.println("Task" + (taskNum - 1) + "updated to: " + task);
    }
    
    public static int getTaskCount() {
        return tasks.size();
    }
}
