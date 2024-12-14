package com.gr1t.taskmanager;

import java.util.List;
import java.util.Scanner;

public class TaskMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice){
                case 1:
                    processCreateTask();
                    break;
                case 2:
                    processViewAllTasks();
                    break;
                case 3:
                    processSaveAsFile();
                    break;
                case 4:
                    processSortByPriority();
                    break;
                case 5:
                    processSortByStatus();
                    break;
                case 6:
                    processMarkCompleted();
                    break;
                case 7:
                    processRemoveByNumber();
                    break;
                case 8:
                    processUpdateTask();
                    break;
                case 9:
                    processSearchByTitle();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Value is not correct, please try again");
            }

        }
    }
    
    private static void displayMenu(){
        System.out.println("1. Create Task");
        System.out.println("2. View TAll Tasks");
        System.out.println("3. Save as file");
        System.out.println("4. Sort by priority");
        System.out.println("5. Sort by status");
        System.out.println("6. Make task completed");
        System.out.println("7. Remove by number");
        System.out.println("8. Update task");
        System.out.println("9. Search by title");
        System.out.println("10. Exit");
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

            if (choice < 1 || choice > 10) { // Check input for correct range
                System.out.println("Invalid input. Please enter a number in range 1-10: ");
                continue;
            }
            break;
        }
        return choice;
    }

    private static void processCreateTask() {
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
                System.out.println("Value is not correct, please try again: ");
            }
        }
        Task task = TaskManager.createTask(title, description, priority);
        System.out.println("Your new task: " + task);
    }

    private static void processSaveAsFile(){
        scanner.nextLine();
        System.out.println("Enter file name: ");
        String fileName = scanner.nextLine();
        TaskFileService.saveTasks(TaskManager.getAllTasks(), fileName);
    }
    private static void processSortByPriority() {
        TaskManager.sortByPriority();
        System.out.println("Successfully sorted by priority!");
        System.out.println("Your sorted list: ");
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());
    }
    private static void processSortByStatus() {
        TaskManager.sortByStatus();
        System.out.println("List after sorting by status: ");
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());
    }
    private static void processMarkCompleted() {
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());
        System.out.println("Enter task number to mark as completed:  ");
        int taskNumber = scanner.nextInt();
        TaskManager.markCompleted(taskNumber);
    }

    private static void processRemoveByNumber() {
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());
        System.out.println("Enter task number to remove: ");
        int taskNumber = scanner.nextInt();
        TaskManager.removeTask(taskNumber);
    }

    private static void processViewAllTasks() {
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());
    }

    private static void processUpdateTask() {
        TaskPrintService.printAllTasks(TaskManager.getAllTasks());
        System.out.println("Enter task number to update: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        if (taskNum < 1 || taskNum > TaskManager.getTaskCount()) {
            System.out.println("Invalid task number");
            return;
        }

        String newTitle;
        String newDescription;

        while (true) {
            System.out.println("Enter new title: ");
            newTitle = scanner.nextLine();
            if (newTitle.isEmpty()) {
                System.out.println("Title cannot be empty. Please enter a valid title: ");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Enter new description: ");
            newDescription = scanner.nextLine();

            if (newDescription.isEmpty()) {
                System.out.println("Description cannot be empty. Please enter a valid description: ");
            } else {
                break;
            }
        }

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
        TaskManager.updateTask(taskNum, newTask);
    }

    private static void processSearchByTitle() {
        scanner.nextLine();
        System.out.println("Enter the title you want to find: ");
        String searchQuery = scanner.nextLine();

        List<Task> matchingTasks = TaskManager.searchByTitle(searchQuery);
        TaskPrintService.printTaskFound(matchingTasks);
    }
}
