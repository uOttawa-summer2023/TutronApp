package com.example.tutronapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends User implements Serializable {

    private boolean isSuspended;
    private boolean isTemporarySuspended;
    private String suspensionEndDate;
    private String educationLevel;
    private String nativeLanguage;
    private String description;

    private List<TutorTopics> profileTopics;
    private List<TutorTopics> offeredTopics;

    public Tutor(int ID, String email, String password, String firstName, String lastName,
                 String educationLevel, String nativeLanguage, String description) {
        super(ID, email, password, firstName, lastName);
        this.educationLevel = educationLevel;
        this.nativeLanguage = nativeLanguage;
        this.description = description;

        profileTopics = new ArrayList<>();
        offeredTopics = new ArrayList<>();
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isTemporarySuspended() {
        return isTemporarySuspended;
    }

    public void setTemporarySuspended(boolean temporarySuspended) {
        isTemporarySuspended = temporarySuspended;
    }

    public String getSuspensionEndDate() {
        return suspensionEndDate;
    }

    public void setSuspensionEndDate(String suspensionEndDate) {
        this.suspensionEndDate = suspensionEndDate;
    }

    // Methods to add/remove topics to/from the profile and offered topics list

    public void addTopicToProfile(TutorTopics topic) {
        profileTopics.add(topic);
    }

    public void removeTopicFromProfile(TutorTopics topic) {
        profileTopics.remove(topic);
    }

    public void addToOfferedTopics(TutorTopics topic) {
        offeredTopics.add(topic);
    }

    public void removeFromOfferedTopics(TutorTopics topic) {
        offeredTopics.remove(topic);
    }

    public List<TutorTopics> getProfileTopics() {
        return profileTopics;
    }

    public List<TutorTopics> getOfferedTopics() {
        return offeredTopics;
    }
}
