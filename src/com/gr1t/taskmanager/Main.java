package com.gr1t.taskmanager;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Save as file");
            System.out.println("4. Sort by priority");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;

            while (true) { // Check input for correct number
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <=5){ // Check input for correct range
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number in range 1-3: ");
                    }
                } else {
                        System.out.println("Invalid input. Enter a number:");
                        scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    Priority priority;
                    while (true) {
                        try {
                            System.out.println("Enter priority (LOW, MEDIUM, HIGH): ");
                            priority = Priority.valueOf(scanner.nextLine().toUpperCase());
                            break;
                        } catch (Exception e) {
                            System.out.println("ENTER ONLY RIGHT PRIORITY!!!: ");
                        }
                    }
                    Task task = TaskManager.createTask(title, description, priority);
                    System.out.println("Your task: " + task);
                    break;
                case 2:
                    System.out.println("All tasks: ");
                    TaskManager.printAllTasks();
                    break;
                case 3:
                    System.out.println("Enter file name: ");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    TaskManager.saveTasks(fileName);
                    break;
                case 4:
                    TaskManager.sortByPriority();
                    System.out.println("Successfully sorted by priority!");
                    break;
                case 5:
                    System.out.println("Leave a review on zversilneykitayca.com");
                    return;
                default:
                    System.out.println("ERROR?");
            }
        }
    }
}