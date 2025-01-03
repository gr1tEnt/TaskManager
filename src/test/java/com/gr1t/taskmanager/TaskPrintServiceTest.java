package com.gr1t.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskPrintServiceTest {

    private List<Task> tasks;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        TaskManager.getAllTasks().clear();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testPrintAllTasksWithExistingTasks() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);

        TaskPrintService.printAllTasks(TaskManager.getAllTasks());

        String expectedOutput = "1 " + task1 + System.lineSeparator() + "2 " + task2 + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testPrintAllTasksWithNonExistentTasks() {
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());

        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void testPrintAllTasksWithOneExistingTask() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);

        TaskPrintService.printAllTasks(TaskManager.getAllTasks());

        String expectedOutput = "1 " + task1 + System.lineSeparator();;
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testPrintTaskFoundWithEmptyList() {
        tasks = List.of();

        TaskPrintService.printTaskFound(tasks);

        assertEquals("No tasks found." + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testPrintTaskFoundWithSingleTask() {
        Task task1 = new Task("Task 1", "Description 1", Priority.LOW);
        tasks = List.of(task1);

        TaskPrintService.printTaskFound(tasks);

        String expectedOutput = "Found tasks: " + tasks + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testPrintTaskFoundWithNonEmptyList() {
        Task task1 = new Task("Task 1", "Description 1", Priority.LOW);
        Task task2 = new Task("Task 2", "Description 2", Priority.HIGH);
        tasks = List.of(task1, task2);

        TaskPrintService.printTaskFound(tasks);

        String expectedOutput = "Found tasks: " + tasks + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

}