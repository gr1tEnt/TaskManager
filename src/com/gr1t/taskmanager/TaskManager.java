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
        List<Task> sortedByPriority = new ArrayList<>(tasks);
        sortedByPriority.sort(Comparator.comparing(Task::getPriority));
        tasks.clear();
        tasks.addAll(sortedByPriority);
    }

    public static void removeTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
        } else {
            System.out.println("Invalid task number. Please try again");
        }
    }


    public static void sortByStatus() {
        List<Task> sortedByStatus = new ArrayList<>(tasks);
        sortedByStatus.sort(Comparator.comparing(Task::isCompleted));
        tasks.clear();
        tasks.addAll(sortedByStatus);
    }

    public static void markCompleted(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setCompleted(true);
            System.out.println("Task is completed: " + task);
        } else {
            System.out.println("Invalid task number. Please try again");
        }
    }

    public static void updateTask(int taskNum, Task updatedTask) {
        tasks.set(taskNum - 1, updatedTask);
        System.out.println("Task " + taskNum + " updated to: " + updatedTask);
    }

    public static List<Task> searchByTitle (String searchQuery) {
        List<Task> matchingTask = new ArrayList<>();

        for (Task task:tasks) {
            if (task.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                matchingTask.add(task);
            }
        }
        return matchingTask;
    }

    public static int getTaskCount() {
        return tasks.size();
    }
}
