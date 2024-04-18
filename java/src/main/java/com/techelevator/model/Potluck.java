package com.techelevator.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Potluck {
    private int potluckId;
    private String eventName;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String dietaryRestrictions;
    private boolean isRecurring;
    private int frequencyDays;
    private String location;
    private boolean isPrivate;
    private int userId;
    private boolean isCompleted;

    public Potluck() {}
    public Potluck(int potluckId, String eventName, String description, LocalDate eventDate, LocalTime eventTime,
                   String dietaryRestrictions, boolean isRecurring, int frequencyDays, String location,
                   boolean isPrivate, int userId, boolean isCompleted) {
        this.potluckId = potluckId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.dietaryRestrictions = dietaryRestrictions;
        this.isRecurring = isRecurring;
        this.frequencyDays = frequencyDays;
        this.location = location;
        this.isPrivate = isPrivate;
        this.userId = userId;
        this.isCompleted = isCompleted;
    }


    public int getPotluckId() {
        return potluckId;
    }

    public void setPotluckId(int potluckId) {
        this.potluckId = potluckId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = LocalDate.parse(eventDate);
    }

    public String getEventTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return this.eventTime.format(formatter);
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

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public int getFrequencyDays() {
        return this.frequencyDays;
    }

    public void setFrequencyDays(int frequencyDays) {
        this.frequencyDays = frequencyDays;
    }
    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean Private) {
        isPrivate = Private;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}