package com.github.dataemperor.student_mentor_management_system;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.dataemperor.student_mentor_management_system.classes.Program;
import com.github.dataemperor.student_mentor_management_system.classes.Student;
import com.github.dataemperor.student_mentor_management_system.database.Database;
import java.io.File;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseTest {

    private Database database;
    private static final String dbName = "TestingDatabase.db";

    @AfterEach
    private void deleteDatabase() {
        if (database != null) {
            database.closeConnection();
            database = null;
        }

        File databaseFile = new File(dbName);
        databaseFile.delete();
    }

    @BeforeEach
    void setUp() {
        database = new Database(dbName);
        database.initializeTables();
    }

    Student getSampleStudent() {
        Student sampleStudent = new Student(
            "20220520",
            "Jayathu",
            "Chankama",
            "Fernando",
            "tjchankamafernando@gmail.com",
            "0716306373",
            "12345",
            false,
            "12345",
            "CM2601"
        );

        return sampleStudent;
    }

    @Test
    void insertStudentSuccessPath() throws SQLException {
        Student insertSuccessStudent = getSampleStudent();

        assertDoesNotThrow(
            () -> database.insertStudent(insertSuccessStudent),
            "Insertion succeeded"
        );
    }

    @Test
    void duplicateInsertsStudentId() throws SQLException {
        Program insertOriginalProgram = new Program(
            "CM2601",
            "Object Orientated Programming",
            2
        );
        Student insertOriginalIdStudent = getSampleStudent();

        Student insertDuplicateIdStudent = new Student(
            "20220520",
            "Jayathu",
            "Chankama",
            "Fernando",
            "tjchankamafernando@gmail.com",
            "0716306373",
            "12345",
            false,
            "12345",
            "CM2601"
        );

        assertDoesNotThrow(
            () -> database.insertStudent(insertOriginalIdStudent),
            "Should pass since its in the happy path"
        );

        SQLException thrown = assertThrows(
            SQLException.class,
            () -> database.insertStudent(insertDuplicateIdStudent),
            "This will throw an error because of the duplicate ID"
        );
        System.out.println(
            "Actual SQLException message: " + thrown.getMessage()
        );
        assertTrue(
            thrown
                .getMessage()
                .contains("UNIQUE constraint failed: STUDENT.student_id"),
            "Error message should indicate unique constraint failure"
        );
    }

    @Test
    void validateDuplicateEmail() throws SQLException {
        Student insertDuplicateEmailStudent = getSampleStudent();
        database.insertStudent(insertDuplicateEmailStudent);
        assertTrue(
            database.validateEmail("tjchankamafernando@gmail.com"),
            "Email shouldn't be in database"
        );
    }

    @Test
    void validateDuplicatePassword() throws SQLException {
        Student validateDuplicatePasswordStudent = getSampleStudent();
        database.insertStudent(validateDuplicatePasswordStudent);
        assertTrue(
            database.validatePassword("12345"),
            "Password shouldn't be in database"
        );
    }
}
