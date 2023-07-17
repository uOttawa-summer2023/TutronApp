package com.example.tutronapp;

public class Complaint {
    private int id;
    private String description;
    private String associatedTutor;
    private boolean isSuspended;
    private boolean isTemporarySuspension;
    private String suspensionEndDate;

    // Constructor
    public Complaint(int id, String description, String associatedTutor, boolean isSuspended) {
        this.id = id;
        this.description = description;
        this.associatedTutor = associatedTutor;
        this.isSuspended = isSuspended;
        this.isTemporarySuspension = false; // Default value is false
        this.suspensionEndDate = ""; // Default value is an empty string
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssociatedTutor() {
        return associatedTutor;
    }

    public void setAssociatedTutor(String associatedTutor) {
        this.associatedTutor = associatedTutor;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isTemporarySuspension() {
        return isTemporarySuspension;
    }

    public void setTemporarySuspension(boolean temporarySuspension) {
        isTemporarySuspension = temporarySuspension;
    }

    public String getSuspensionEndDate() {
        return suspensionEndDate;
    }

    public void setSuspensionEndDate(String suspensionEndDate) {
        this.suspensionEndDate = suspensionEndDate;
    }

    // Updated toString() method
    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", associatedTutor='" + associatedTutor + '\'' +
                ", isSuspended=" + isSuspended +
                ", isTemporarySuspension=" + isTemporarySuspension +
                ", suspensionEndDate='" + suspensionEndDate + '\'' +
                '}';
    }
}
