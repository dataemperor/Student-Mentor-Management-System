package com.github.dataemperor.student_mentor_management_system.tasks;

import com.github.dataemperor.student_mentor_management_system.classes.*;
import com.github.dataemperor.student_mentor_management_system.database.*;
import java.sql.SQLException;

public class RegisterStudent implements Runnable {

    private final String studentId;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String homeNumber;
    private final boolean isMentored;
    private final Program program;
    private final String password;

    private final Database database;

    public RegisterStudent(
        String studentId,
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phoneNumber,
        String homeNumber,
        boolean isMentored,
        Program program,
        String password,
        Database database
    ) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeNumber = homeNumber;
        this.isMentored = false;
        this.program = program;
        this.password = password;
        this.database = database;
    }

    @Override
    public void run() {
        System.out.println("THIS IS RUNNING!");
        try {
            if (database.validateStudentId(studentId)) {
                System.out.println("Student ID is invalid");
                return;
            }

            if (database.validateEmail(email)) {
                System.out.println("Email is invalid");
                return;
            }

            if (database.validatePassword(password)) {
                System.out.println("Password is invalid");
                return;
            }

            if (database.validateProgram(program)) {
                System.out.println("Program is invalid");
                return;
            }

            if (database.validatePhoneNumber(phoneNumber)) {
                System.out.println("Phone number is invalid");
                return;
            }
            /*
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeNumber = homeNumber;
        this.isMentored = false;
        this.program = program;
        this.password = password;
        this.database = database;
        */
            Student newStudent = new Student(
                studentId,
                firstName,
                middleName,
                lastName,
                email,
                phoneNumber,
                homeNumber,
                isMentored,
                password,
                program
            );

            database.insertStudent(newStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
