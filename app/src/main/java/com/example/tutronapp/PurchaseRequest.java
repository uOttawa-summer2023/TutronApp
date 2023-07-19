package com.example.tutronapp;

public class PurchaseRequest {

    private int lessonId;
    private int tutorId;
    private boolean isApproved;
    private boolean isCompleted;

    public PurchaseRequest(int lessonId, int tutorId) {
        this.lessonId = lessonId;
        this.tutorId = tutorId;
        this.isApproved = false;
        this.isCompleted = false;
    }

    // Add getters and setters (if needed) and other methods as required
}
