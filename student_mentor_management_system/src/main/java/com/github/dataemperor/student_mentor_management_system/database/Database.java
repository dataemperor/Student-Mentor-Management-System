package com.github.dataemperor.student_mentor_management_system.database;

import com.github.dataemperor.student_mentor_management_system.classes.Program;
import com.github.dataemperor.student_mentor_management_system.classes.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection connection = null;
    String[][] programs = {
        { "CM2601", "Object Orientated Development" },
        { "CM2602", "Artificial Intelligence" },
        { "CM2603", "Data Science Group Project" },
        { "CM2604", "Machine Learning" },
        { "CM2605", "Simulation and Modelling Techniques" },
        { "CM2606", "Data Engineering" },
        { "CM2607", "Advanced Mathematics for Data Science" },
    };

    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                "jdbc:sqlite:ManagementSystemDatabase.db"
            );
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys = ON;");
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(1);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null; // preventing reuse
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    public void initializeTables() {
        if (connection == null) {
            System.err.println("Connection is null, cannot initalize tables");
            System.exit(1);
        }
        String sqlProgram =
            "CREATE TABLE IF NOT EXISTS PROGRAM (" +
            "program_id VARCHAR(10) UNIQUE NOT NULL," +
            "program_name VARCHAR(30) NOT NULL," +
            "program_year INTEGER NOT NULL" +
            "PRIMARY KEY (program_id)" +
            " )";
        String sqlStudent =
            "CREATE TABLE IF NOT EXISTS STUDENT (" +
            "student_id VARCHAR(10) NOT NULL," +
            "first_name VARCHAR(30) NOT NULL," +
            "middle_name VARCHAR(30)," +
            "last_name VARCHAR(30) NOT NULL," +
            "email VARCHAR(60) NOT NULL," +
            "phone_number VARCHAR(12) UNIQUE," +
            "home_number VARCHAR(12) NOT NULL," +
            "year VARCHAR(1) NOT NULL," +
            "password VARCHAR(12) NOT NULL UNIQUE," +
            "program_id VARCHAR(10) NOT NULL," +
            "is_mentored BOOLEAN NOT NULL," +
            "FOREIGN KEY (program_id) REFERENCES PROGRAM(program_id)" +
            " )";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlProgram);
            statement.executeUpdate(sqlStudent);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(1);
        }
    }

    boolean placeholder = false;

    // checks if the email is in the database
    public boolean validateEmail(String email) throws SQLException {
        String selectEmailQuery =
            "SELECT COUNT(*) FROM STUDENT WHERE email = ?";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(
                selectEmailQuery
            );
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    // checks if the password is a new password
    public boolean validatePassword(String password) throws SQLException {
        String selectPasswordQuery =
            "SELECT COUNT(*) FROM STUDENT WHERE password = ?";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(
                selectPasswordQuery
            );
        ) {
            preparedStatement.setString(1, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // validates program id, name and year
    public boolean validateProgram(Program program) {
        String programId = program.getProgramId();
        String programName = program.getProgramName();
        int programYear = program.getProgramYear();
        boolean validProgramId = false;
        boolean validProgramName = false;
        boolean validProgramYear = false;

        for (String[] presetProgram : programs) {
            if (presetProgram[0] == programId) {
                validProgramId = true;
            }

            if (presetProgram[1] == programName) {
                validProgramName = true;
            }

            if (programYear > 0 && programYear < 5) {
                validProgramYear = true;
            }
        }

        if (validProgramId && validProgramName && validProgramYear) {
            return true;
        }

        return false;
    }

    // checks if the phone number is in the database
    public boolean validatePhoneNumber(String phoneNumber) throws SQLException {
        String selectPhoneNumberQuery =
            "SELECT COUNT(*) FROM STUDENT WHERE phone_number = ?";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(
                selectPhoneNumberQuery
            );
        ) {
            preparedStatement.setString(1, phoneNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // checks if the student id is in the database
    public boolean validateStudentId(String studentId) throws SQLException {
        String selectStudentIdQuery =
            "SELECT COUNT(*) FROM STUDENT WHERE student_id = ?";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(
                selectStudentIdQuery
            );
        ) {
            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void insertStudent(Student student) throws SQLException {
        String insertStudentQuery =
            "INSERT INTO STUDENT (" +
            "first_name, middle_name, last_name," +
            "email, phone_number, home_number" +
            "year, password, program_id" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?,?, ?);";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(
                insertStudentQuery
            );
        ) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getMiddleName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmailAddress());
            preparedStatement.setString(5, student.getContactNumber());
            preparedStatement.setString(6, student.getHomeNumber());
            preparedStatement.setInt(7, student.getProgram().getProgramYear());
            preparedStatement.setString(8, student.getPassword());
            preparedStatement.setString(9, student.getProgram().getProgramId());

            preparedStatement.executeUpdate();
        }
    }
}
