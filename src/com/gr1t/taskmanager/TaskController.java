package com.gr1t.taskmanager;

import java.util.Scanner;

public class TaskController {
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice){
                case 1:
                    createTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    saveAsFile();
                    break;
                case 4:
                    sortByPriority();
                    break;
                case 5:
                    sortByStatus();
                    break;
                case 6:
                    markCompleted();
                    break;
                case 7:
                    removeByNumber();
                    break;
                case 8:
                    updateTask();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Value is not correct, please try again");
            }

        }
    }
    
    private static void showMenu(){
        System.out.println("1. Create Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Save as file");
        System.out.println("4. Sort by priority");
        System.out.println("5. Sort by status");
        System.out.println("6. Make task completed");
        System.out.println("7. Remove by number");
        System.out.println("8. Update task");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        int choice;
        while (true) { // Check input for number
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Enter a number:");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            if (choice < 1 || choice > 9) { // Check input for correct range
                System.out.println("Invalid input. Please enter a number in range 1-3: ");
                continue;
            }
            break;
        }
        return choice;
    }

    private static void createTask() {
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
        System.out.println("Your new task: " + task);
    }
    private static void saveAsFile(){
        System.out.println("Enter file name: ");
        scanner.nextLine();
        String fileName = scanner.nextLine();
        TaskManager.saveTasks(fileName);
    }
    private static void sortByPriority() {
        TaskManager.sortByPriority();
        System.out.println("Successfully sorted by priority!");
        System.out.println("Your sorted list: ");
        TaskManager.printAllTasks();
    }
    private static void sortByStatus() {
        TaskManager.sortByStatus();
        System.out.println("List after sorting by status: ");
        TaskManager.printAllTasks();
    }
    private static void markCompleted() {
        TaskManager.printAllTasks();
        System.out.println("Enter number of task: ");
        int markByNumber = scanner.nextInt();
        TaskManager.markCompleted(markByNumber);
    }

    private static void removeByNumber() {
        TaskManager.printAllTasks();
        System.out.println("Enter number of task: ");
        int taskNumber = scanner.nextInt();
        TaskManager.removeTask(taskNumber);
        System.out.println("List after removing: ");
        TaskManager.printAllTasks();
    }

    private static void viewTasks() {
        System.out.println("All tasks: ");
        TaskManager.printAllTasks();
    }

    private static void updateTask() {
        TaskManager.printAllTasks();
        System.out.println("Enter number of task you wanna update: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        if (taskNum < 1 || taskNum > TaskManager.getTaskCount()) {
            System.out.println("Task with this number does not exist!");
            return;
        }

        System.out.println("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.println("Enter new description: ");
        String newDescription = scanner.nextLine();

        Priority newPriority;
        while (true) {
            try {
                System.out.println("Enter priority (LOW, MEDIUM, HIGH): ");
                newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (Exception e) {
                System.out.println("Value is not correct, please try again ");
            }
        }
        
        Task newTask = new Task(newTitle, newDescription, newPriority);
        TaskManager.updateTask(taskNum, newTask);    }
}
