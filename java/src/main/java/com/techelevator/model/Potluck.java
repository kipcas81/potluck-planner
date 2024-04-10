package com.techelevator.model;


import java.util.ArrayList;
import java.util.List;

public class Potluck {
    private int potluckId;
    private int userId;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String location;
    private List dietaryRestrictions;
    private boolean isRecurring;
    private int frequencyDays;


    public int getPotluckId() {
        return potluckId;
    }

    public void setPotluckId(int id) {
        this.potluckId = potluckId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public int getFrequencyDays() {
        return frequencyDays;
    }

    public void setFrequencyDays(int frequencyDays) {
        this.frequencyDays = frequencyDays;
    }
}