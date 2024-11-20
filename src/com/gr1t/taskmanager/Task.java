package com.gr1t.taskmanager;

public class Task {
    private int id;
    private String description;
    private String priority;
    private boolean isCompleted;

    public Task(boolean isCompleted, String priority, String description, int id) {
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.description = description;
        this.id = id;
    }
}
