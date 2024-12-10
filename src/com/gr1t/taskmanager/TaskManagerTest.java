package com.gr1t.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class TaskManagerTest {

    private List<Task> tasks;

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        TaskManager.getAllTasks().clear();

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testSortByStatusWithEmptyList() {
        TaskManager.sortByStatus();
        assertTrue(TaskManager.getAllTasks().isEmpty(), "The task list should be empty after sorting by status.");
    }

    @Test
    public void testSortByStatusWithMixedList() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);
        Task task3 = TaskManager.createTask("Task 3", "description 3", Priority.LOW);

        TaskManager.markCompleted(2);
        TaskManager.markCompleted(3);

        TaskManager.sortByStatus();

        assertFalse(TaskManager.getAllTasks().get(0).isCompleted(), "The first task should be not completed.");
        assertTrue(TaskManager.getAllTasks().get(1).isCompleted(), "The second task should be completed.");
        assertTrue(TaskManager.getAllTasks().get(2).isCompleted(), "The third  task should be completed.");
    }

    @Test
    public void testSortByStatusWithAllNotCompleted() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);
        Task task3 = TaskManager.createTask("Task 3", "description 3", Priority.LOW);

        TaskManager.sortByStatus();

        assertFalse(TaskManager.getAllTasks().get(0).isCompleted(), "The first task should be not completed.");
        assertFalse(TaskManager.getAllTasks().get(1).isCompleted(), "The second task should be not completed.");
        assertFalse(TaskManager.getAllTasks().get(2).isCompleted(), "The third task should be not completed.");

    }

    @Test
    public void testSortByStatusWithAllCompleted() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);
        Task task3 = TaskManager.createTask("Task 3", "description 3", Priority.LOW);

        TaskManager.markCompleted(1);
        TaskManager.markCompleted(2);
        TaskManager.markCompleted(3);

        TaskManager.sortByStatus();

        assertTrue(TaskManager.getAllTasks().get(0).isCompleted(), "The first task should be completed.");
        assertTrue(TaskManager.getAllTasks().get(1).isCompleted(), "The second task should be completed.");
        assertTrue(TaskManager.getAllTasks().get(2).isCompleted(), "The third task should be completed.");
    }

    @Test
    public void testSortByPriorityWithEmptyList() {
        TaskManager.sortByPriority();
        assertTrue(TaskManager.getAllTasks().isEmpty());
    }

    @Test
    public void testSortByPriorityWithAllMixedList() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.MEDIUM);
        Task task3 = TaskManager.createTask("Task 2", "description 2", Priority.MEDIUM);
        Task task4 = TaskManager.createTask("Task 3", "description 3", Priority.HIGH);

        TaskManager.sortByPriority();

        assertEquals(Priority.HIGH, task4.getPriority(), "Task 4 should have high priority.");
        assertEquals(Priority.MEDIUM, task3.getPriority(), "Task 3 should have high priority.");
        assertEquals(Priority.MEDIUM, task2.getPriority(), "Task 2 should have high priority.");
        assertEquals(Priority.LOW, task1.getPriority(), "Task 1 should have high priority.");
    }

    @Test
    public void testMarkCompletedWithValidTaskNumber() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);

        TaskManager.markCompleted(2);

        assertFalse(TaskManager.getAllTasks().get(0).isCompleted(), "Task 1 should be not completed.");
        assertTrue(TaskManager.getAllTasks().get(1).isCompleted(), "Task 2 should be completed.");
    }

    @Test
    public void testMarkCompletedWithInvalidTaskNumber(){
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);

        TaskManager.markCompleted(5);

        assertFalse(task1.isCompleted(), "Task 1 should be not completed.");

        assertTrue(outputStream.toString().contains("Invalid task number"), "Console output should indicate an invalid task number.");
    }
}