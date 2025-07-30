package com.github.dataemperor.student_mentor_management_system;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter middle name");
        String middleName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter contact number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter home number");
        String homeNumber = scanner.nextLine();
        System.out.println("Enter program id");
        String programId = scanner.nextLine();
        System.out.println("Enter program year");
        int programYear = scanner.nextInt();
        System.out.println("Enter password ");
        int password = scanner.nextInt();
    }
}
