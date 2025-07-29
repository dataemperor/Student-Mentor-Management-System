package com.github.dataemperor.student_mentor_management_system.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection connection = null;

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
            "PRIMARY KEY (program_id)" +
            " )";
        String sqlStudent =
            "CREATE TABLE IF NOT EXISTS STUDENT (" +
            "first_name VARCHAR(30) NOT NULL," +
            "middle_name VARCHAR(30)," +
            "last_name VARCHAR(30) NOT NULL," +
            "email VARCHAR(60) NOT NULL," +
            "phone_number VARCHAR(12) UNIQUE," +
            "home_number VARCHAR(12) NOT NULL," +
            "year VARCHAR(1) NOT NULL," +
            "password VARCHAR(12) NOT NULL UNIQUE," +
            "program_id VARCHAR(10) NOT NULL," +
            "FOREIGN KEY (program_id) REFERENCES PROGRAM(program_id)" +
            " )";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlProgram);
            statement.executeUpdate(sqlStudent);

            // populating the program table
            String[][] programs = {
                { "CM2601", "Object Orientated Development" },
                { "CM2602", "Artificial Intelligence" },
                { "CM2603", "Data Science Group Project" },
                { "CM2604", "Machine Learning" },
                { "CM2605", "Simulation and Modelling Techniques" },
                { "CM2606", "Data Engineering" },
                { "CM2607", "Advanced Mathematics for Data Science" },
            };

            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT OR IGNORE INTO PROGRAM (program_id, program_name) VALUES (?, ?)"
            );

            for (String[] program : programs) {
                preparedStatement.setString(1, program[0]);
                preparedStatement.setString(2, program[1]);
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(1);
        }
    }
}
