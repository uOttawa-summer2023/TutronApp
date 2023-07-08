package com.example.tutronapp;

public class Complaints {
    private String studentName;
    private String tutorName;
    private String description;

    public Complaints(String studentName, String tutorName, String description) {
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.description = description;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getDescription() {
        return description;
    }
}

