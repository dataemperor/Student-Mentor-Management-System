package com.github.dataemperor.student_mentor_management_system;

import com.github.dataemperor.student_mentor_management_system.classes.Program;
import com.github.dataemperor.student_mentor_management_system.database.Database;
import com.github.dataemperor.student_mentor_management_system.tasks.RegisterStudent;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        database.initializeTables();

        System.out.println("Enter student id");
        String studentId = scanner.nextLine().toLowerCase();
        System.out.println("Enter first name");
        String firstName = scanner.nextLine().toLowerCase();
        System.out.println("Enter middle name");
        String middleName = scanner.nextLine().toLowerCase();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine().toLowerCase();
        System.out.println("Enter email");
        String email = scanner.nextLine().toLowerCase();
        System.out.println("Enter contact number");
        String contactNumber = scanner.nextLine().toLowerCase();
        System.out.println("Enter home number");
        String homeNumber = scanner.nextLine().toLowerCase();
        System.out.println("Enter program id");
        String programId = scanner.nextLine().toLowerCase();
        System.out.println("Enter password ");
        scanner.nextLine(); // to prevent scanner bug
        String password = scanner.nextLine().toLowerCase();

        Boolean isMentored = false;

        RegisterStudent registerNewStudent = new RegisterStudent(
            studentId,
            firstName,
            middleName,
            lastName,
            email,
            contactNumber,
            homeNumber,
            isMentored,
            programId,
            password,
            database
        );

        Thread registerNewStudentThread = new Thread(registerNewStudent);
        registerNewStudentThread.start();

        // to ensure that the main thread doesn't close the program before registerNewStudentThread is complete
        try {
            registerNewStudentThread.join();
        } catch (InterruptedException e) {
            System.err.println("InterruptedException: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            scanner.close();
            database.closeConnection();
        }
    }
}
