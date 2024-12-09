package com.gr1t.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        TaskManager.getAllTasks().clear();
    }

    @Test
    public void testSortByStatusWithEmptyList() {
        TaskManager.sortByStatus();
        assertTrue(TaskManager.getAllTasks().isEmpty());
    }

    @Test
    public void testSortByStatusWithMixedList() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);
        Task task3 = TaskManager.createTask("Task 3", "description 3", Priority.LOW);

        TaskManager.markCompleted(2);
        TaskManager.markCompleted(3);

        TaskManager.sortByStatus();

        assertFalse(TaskManager.getAllTasks().get(0).isCompleted());
        assertTrue(TaskManager.getAllTasks().get(1).isCompleted());
        assertTrue(TaskManager.getAllTasks().get(2).isCompleted());
    }

    @Test
    public void testSortByStatusWithAllNotCompleted() {

        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);
        Task task3 = TaskManager.createTask("Task 3", "description 3", Priority.LOW);

        TaskManager.sortByStatus();

        assertFalse(TaskManager.getAllTasks().get(0).isCompleted());
        assertFalse(TaskManager.getAllTasks().get(1).isCompleted());
        assertFalse(TaskManager.getAllTasks().get(2).isCompleted());

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

        assertTrue(TaskManager.getAllTasks().get(0).isCompleted());
        assertTrue(TaskManager.getAllTasks().get(1).isCompleted());
        assertTrue(TaskManager.getAllTasks().get(2).isCompleted());

    }

}