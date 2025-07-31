package com.github.dataemperor.student_mentor_management_system.classes;

public class Program {

    private String programId;
    private String programName;
    private int programYear;

    public Program(String programId, String programName, int programYear) {
        this.programId = programId;
        this.programName = programName;
        this.programYear = programYear;
    }

    public String getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public int getProgramYear() {
        return programYear;
    }
}
