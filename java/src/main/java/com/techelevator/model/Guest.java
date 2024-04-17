package com.techelevator.model;

public class Guest {

    private int potluck_id;

    private int user_id;
    private int guest_id;

    private String guest_first_name;
    private String guest_last_name;

    private String guest_email_address;

    private boolean guest_already_registered;

    public Guest() {}

    public Guest(int potluckId, int userId, int guestId, String guestFirstName, String guestLastName, String guestEmailAddress, boolean guestAlreadyRegistered) {

        this.potluck_id = potluckId;
        this.user_id = userId;
        this.guest_id = guestId;
        this.guest_first_name = guestFirstName;
        this.guest_last_name = guestLastName;
        this.guest_email_address = guestEmailAddress;
        this.guest_already_registered = guestAlreadyRegistered;
    }

    public int getPotluck_id() {
        return potluck_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public int getGuest_id() {
        return guest_id;
    }
    public String getGuest_first_name() {
        return guest_first_name;
    }

    public String getGuest_last_name() {
        return guest_last_name;
    }

    public String getGuest_email_address() {
        return guest_email_address;
    }

    public boolean getGuest_already_registered() {
        return guest_already_registered;
    }

    public void setPotluck_id(int potluckId) {
        this.potluck_id = potluckId;
    }
    public void setUser_id(int userId) {
        this.user_id = userId;
    }
    public void setGuest_id(int guestId) {
        this.guest_id = guestId;
    }

    public void setGuest_first_name(String guestFirstName) {
        this.guest_first_name = guestFirstName;
    }

    public void setGuest_last_name(String guestLastName) {
        this.guest_last_name = guestLastName;
    }

    public void setGuest_email_address(String guestEmailAddress) {
        this.guest_email_address = guestEmailAddress;
    }

    public void setGuest_already_registered(boolean guestAlreadyRegistered) {
        this.guest_already_registered = guestAlreadyRegistered;
    }
}