package com.gr1t.taskmanager;

import javax.sound.midi.Soundbank;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void saveTasks (String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"));) {
            for (String task : tasks) {
                writer.write(task);
            }
            System.out.println("File successfully saved as: " + fileName + ".txt");
        } catch (IOException e) {
            System.out.println("Your fault is: " + e.getMessage());
        }
    }
}
