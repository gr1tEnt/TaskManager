package com.gr1t.taskmanager;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskFileServiceTest {

    private final String fileName = "testTasks";

    @Test
    public void testSaveTasksSuccess() throws IOException {
        List<Task> tasks = Arrays.asList(
                new Task("Task 1", "description 1", Priority.LOW),
                new Task("Task 2", "description 2", Priority.LOW),
                new Task("Task 3", "description 3", Priority.LOW)
        );
        TaskFileService.saveTasks(tasks, fileName);

        Path filePath = Paths.get(fileName+ ".txt");
        assertTrue(Files.exists(filePath), "The file should exist");
        List<String> lines = Files.readAllLines(filePath);

        assertEquals(3, lines.size(), "The file should contain 3 lines");
    }

    @Test
    public void testSaveTasksEmptyList() throws IOException {
        List<Task> tasks = Collections.emptyList();

        TaskFileService.saveTasks(tasks, fileName);

        Path filePath = Paths.get(fileName+ ".txt");
        assertTrue(Files.exists(filePath), "The file should exist");

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(0, lines.size(), "The file should contain 0 lines");
    }

}