package com.techelevator.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Potluck {
    private int potluckId;
    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private List<String> dietaryRestrictions;
    private boolean isRecurring;
    private int frequencyDays;
    private String location;
    private boolean isPrivate;
    private int userId;


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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = LocalDate.parse(eventDate);
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = LocalTime.parse(eventTime);
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
    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}