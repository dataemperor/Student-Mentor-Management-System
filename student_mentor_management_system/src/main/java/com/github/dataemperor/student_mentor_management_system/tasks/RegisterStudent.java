package com.github.dataemperor.student_mentor_management_system.tasks;

import com.github.dataemperor.*;
import com.github.dataemperor.student_mentor_management_system.classes.*;
import com.github.dataemperor.student_mentor_management_system.database.*;

public class RegisterStudent implements Runnable {

    private final String studentId;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String homeNumber;
    private final boolean isMentored;
    private final String programId;
    private final int programYear;
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
        String programId,
        int programYear,
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
        this.programId = programId;
        this.programYear = programYear;
        this.password = password;
        this.database = database;
    }

    @Override
    public void run() {
        try {

        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
