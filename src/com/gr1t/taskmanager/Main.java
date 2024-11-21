package com.gr1t.taskmanager;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter priority (LOW, MEDIUM, HIGH): ");
                    Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());
                    Task task = TaskManager.createTask(title, description, priority);
                    System.out.println("Your task: " + task);
                    break;
                case 2:
                    System.out.println("All tasks: ");
                    TaskManager.printAllTasks();
                    break;
                case 3:
                    System.out.println("Leave a review on zversilneykitayca.com");
                    break;
                default:
                    System.out.println("clown?");
            }
        }
    }
}