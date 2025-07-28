package com.github.dataemperor.student_mentor_management_system.classes;

public class Student {

    private String studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;
    private String homeNumber;
    private Boolean isMentored;
    private String password;
    private Program studentProgram;

    public Student(
        String studentId,
        String firstName,
        String middleName,
        String lastName,
        String emailAddress,
        String contactNumber,
        String homeNumber,
        Boolean isMentored,
        String password,
        String programId,
        String programName,
        int programYear
    ) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.homeNumber = homeNumber;
        this.isMentored = isMentored;
        this.password = password;
        studentProgram = new Program(programId, programName, programYear);
    }

    public Program getProgram() {
        return studentProgram;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setIsMentored(Boolean isMentored) {
        this.isMentored = isMentored;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public Boolean getIsMentored() {
        return isMentored;
    }

    public String getPassword() {
        return password;
    }
}
