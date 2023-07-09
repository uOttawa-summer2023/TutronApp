package com.example.tutronapp;

import java.util.ArrayList;
import java.util.List;

class Topic {
    private String name;
    private int yearsOfExperience;
    private String description;

    public Topic(String name, int yearsOfExperience, String description) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
        this.description = description;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
