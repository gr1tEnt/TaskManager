package com.gr1t.taskmanager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private static final List<Task> tasks = new ArrayList<>();

    public static Task createTask(String title, String description, Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        Task task = new Task(title, description, priority);
        tasks.add(task);
        return task;
    }

    public static void sortByPriority() {
        if (tasks.isEmpty()) {
            return;
        }

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
        if (tasks.isEmpty()) {
            return;
        }

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

    public static List<Task> getAllTasks() {
        return tasks;
    }
}
