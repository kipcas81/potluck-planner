package com.techelevator.model;

public class Friend {
    private int user_id;

    private int friend_id;

    private String friend_first_name;
    private String friend_last_name;

    private String friend_email_address;

    public Friend() {}


    public Friend(int userId, int friendId, String friendFirstName, String friendLastName, String friendEmailAddress) {

        this.user_id = userId;
        this.friend_id = friendId;
        this.friend_first_name = friendFirstName;
        this.friend_last_name = friendLastName;
        this.friend_email_address = friendEmailAddress;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public String getFriend_first_name() {
        return friend_first_name;
    }

    public String getFriend_last_name() {
        return friend_last_name;
    }

    public String getFriend_email_address() {
        return friend_email_address;
    }

    public void setUser_id(int userid) {
        this.user_id = userid;
    }
    public void setFriend_id(int friendId) {
        this.friend_id = friendId;
    }

    public void setFriend_first_name(String friendFirstName) {
        this.friend_first_name = friendFirstName;
    }

    public void setFriend_last_name(String friendLastName) {
        this.friend_last_name = friendLastName;
    }

    public void setFriend_email_address(String friendEmailAddress) {
        this.friend_email_address = friendEmailAddress;
    }

}

