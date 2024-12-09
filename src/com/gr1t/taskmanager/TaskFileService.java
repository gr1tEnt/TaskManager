package com.gr1t.taskmanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskFileService {

    public static void saveTasks(List<Task> tasks, String fileName) {
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
}
