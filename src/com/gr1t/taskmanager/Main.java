package com.gr1t.taskmanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

            System.out.println("Task Manager");
            System.out.println("1. Create Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Tasks by Priority");
            System.out.println("4. Update Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Delete Task");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();




    }
}