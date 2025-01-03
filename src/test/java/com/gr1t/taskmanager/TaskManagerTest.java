package com.gr1t.taskmanager;

import com.gr1t.taskmanager.Priority;
import com.gr1t.taskmanager.Task;
import com.gr1t.taskmanager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testMarkCompletedWithInvalidTaskNumber() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);

        TaskManager.markCompleted(5);

        assertFalse(task1.isCompleted(), "Task 1 should be not completed.");

        assertTrue(outputStream.toString().contains("Invalid task number"), "Console output should indicate an invalid task number.");
    }

    @Test
    public void testRemoveTaskWithValidTaskNumber() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);
        Task task2 = TaskManager.createTask("Task 2", "description 2", Priority.LOW);

        assertEquals(2, TaskManager.getAllTasks().size(), "There should be 2 tasks.");

        TaskManager.removeTask(1);

        assertEquals(1, TaskManager.getAllTasks().size(), "There should be 1 task left.");
        assertEquals(task2, TaskManager.getAllTasks().get(0), "The remaining task should be Task 2.");
    }

    @Test
    public void testRemoveTaskWithInvalidTaskNumber() {
        Task task1 = TaskManager.createTask("Task 1", "description 1", Priority.LOW);

        assertEquals(1, TaskManager.getAllTasks().size(), "There should be 1 task.");

        TaskManager.removeTask(5);

        assertEquals(1, TaskManager.getAllTasks().size());

        assertTrue(outputStream.toString().contains("Invalid task number"), "Console output should indicate an invalid task number.");
    }

    @Test
    public void testRemoveTaskWithEmptyList() {
        TaskManager.removeTask(0);

        assertEquals(0, TaskManager.getAllTasks().size(), "The task list should be empty.");

        assertTrue(outputStream.toString().contains("Invalid task number"), "Console output should indicate an invalid task number.");
    }

    @Test
    public void testCreateTaskSuccess() {
        String title = "Title";
        String description = "Description";
        Priority priority = Priority.HIGH;

        Task task = TaskManager.createTask(title, description, priority);

        assertTrue(TaskManager.getAllTasks().contains(task), "Task list should contain the newly created task");

        assertEquals(title, task.getTitle(), "Task title should match");
        assertEquals(description, task.getDescription(), "Task description should match");
        assertEquals(priority, task.getPriority(), "Task priority should match");
    }

    @Test
    public void testCreateTaskWithNullPriority() {
        String title = "Null Priority Task";
        String description = "Task with null priority";

        assertThrows(IllegalArgumentException.class, () -> {
            TaskManager.createTask(title, description, null);
        }, "Creating a task with null priority should throw IllegalArgumentException");
    }

    @Test
    public void testUpdateTask() {
        TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);
        TaskManager.createTask("Task 2", "Description 2", Priority.MEDIUM);

        Task updatedTask = new Task("Updated Task", "Updated Description", Priority.LOW);

        TaskManager.updateTask(1, updatedTask);

        assertEquals(updatedTask, TaskManager.getAllTasks().get(0), "The first task should be updated.");
        assertNotEquals(updatedTask, TaskManager.getAllTasks().get(1), "The second task should remain unchanged.");
    }

    @Test
    public void testSearchByTitleWithSingleMatch() {
        Task task = TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);

        List<Task> matchingTask = TaskManager.searchByTitle("task");

        assertEquals(1, matchingTask.size(), "Result should contain one matching task");

        assertEquals("Task 1", matchingTask.get(0).getTitle(), "The title of the matching task should be 'Task 1' ");
    }

    @Test
    public void testSearchByTitleWithMultipleMatches() {
        TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);
        TaskManager.createTask("Task 2", "Description 2", Priority.MEDIUM);

        List<Task> matchingTask = TaskManager.searchByTitle("task");

        assertEquals(2, matchingTask.size(), "Result should contain two matching tasks");

        assertEquals("Task 1", matchingTask.get(0).getTitle(), "The title of the matching task should be 'Task 1' ");
        assertEquals("Task 2", matchingTask.get(1).getTitle(), "The title of the matching task should be 'Task 2' ");
    }

    @Test
    public void testSearchByTitleWithNoMatches() {
        TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);

        List<Task> matchingTask = TaskManager.searchByTitle("list");

        assertEquals(0, matchingTask.size(), "Result should not contain matching tasks");
    }

    @Test
    public void testSearchByTitleWithEmptyQuery() {
        TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);
        TaskManager.createTask("Task 2", "Description 2", Priority.MEDIUM);
        TaskManager.createTask("Task 3", "Description 3", Priority.LOW);

        List<Task> matchingTask = TaskManager.searchByTitle("");

        assertEquals(3, matchingTask.size(), "Result should contain all tasks for an empty query");
    }

    @Test
    public void testSearchByTitleWithUppercaseQuery() {
        TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);
        TaskManager.createTask("Task 2", "Description 2", Priority.MEDIUM);

        List<Task> matchingTask = TaskManager.searchByTitle("TASK");

        assertEquals(2, matchingTask.size(), "Result should contain two matching task");
    }

    @Test
    public void testGetTaskCountWithExistingTasks() {
        TaskManager.createTask("Task 1", "Description 1", Priority.HIGH);
        TaskManager.createTask("Task 2", "Description 2", Priority.MEDIUM);

        assertEquals(2, TaskManager.getTaskCount(), "There should be 2 tasks");
    }

    @Test
    public void testGetTaskCountWithNoTasks() {
        assertEquals(0, TaskManager.getTaskCount(), "There should be no tasks");
    }

}