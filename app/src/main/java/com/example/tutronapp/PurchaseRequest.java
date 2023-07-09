package com.example.tutronapp;

// PurchaseRequest class representing a purchase request
class PurchaseRequest {
    private String topic;
    private String studentName;
    private String date;
    private String time;
    private boolean approved;

    public PurchaseRequest(String topic, String studentName, String date, String time) {
        this.topic = topic;
        this.studentName = studentName;
        this.date = date;
        this.time = time;
        this.approved = false;
    }

    public String getTopic() {
        return topic;
    }

    public void setApproved(boolean approve) {
        approved = approve;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
    public boolean getApproved(){
        return approved;
    }
}
