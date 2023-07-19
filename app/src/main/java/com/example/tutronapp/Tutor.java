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

    // Methods to add, remove, and offer topics

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

    public boolean isTopicOffered(TutorTopics topic) {
        return offeredTopics.contains(topic);
    }

    // Method to check if a topic exists in the tutor's profile topics
    public boolean isTopicInProfile(TutorTopics topic) {
        return profileTopics.contains(topic);
    }

    public int getNumOfferedTopics() {
        return offeredTopics.size();
    }

    public List<TutorTopics> getProfileTopics() {
        return profileTopics;
    }

    public List<TutorTopics> getOfferedTopics() {
        return offeredTopics;
    }

    // Additional methods to check if the maximum limits are reached

    public boolean canOfferMoreTopics() {
        return offeredTopics.size() < 5;
    }

    public boolean canAddMoreTopics() {
        return profileTopics.size() + offeredTopics.size() < 20;
    }

    // Methods to find topics in profile and offered topics

    public TutorTopics findTopicInProfile(String topicName) {
        for (TutorTopics topic : profileTopics) {
            if (topic.getTopicName().equalsIgnoreCase(topicName)) {
                return topic;
            }
        }
        return null;
    }

    public TutorTopics findTopicInOfferedTopics(String topicName) {
        for (TutorTopics topic : offeredTopics) {
            if (topic.getTopicName().equalsIgnoreCase(topicName)) {
                return topic;
            }
        }
        return null;
    }
}
