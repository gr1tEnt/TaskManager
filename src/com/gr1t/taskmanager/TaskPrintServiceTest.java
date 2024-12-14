package com.gr1t.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class TaskPrintServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        TaskManager.getAllTasks().clear();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testPrintAllTasksWithExistedTask() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);

        TaskPrintService.printAllTasks(TaskManager.getAllTasks());

        String expectedOutput = "1 " + task1 + System.lineSeparator() + "2 " + task2 + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

}