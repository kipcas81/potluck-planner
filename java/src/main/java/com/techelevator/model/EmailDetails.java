package com.techelevator.model;

import jdk.jfr.DataAmount;

public class EmailDetails {


    private String to;
    private String body;
    private String subject;

    public EmailDetails() {}

    public EmailDetails(String to, String body, String subject) {
        this.to = to;
        this.body = body;
        this.subject = subject;
    }

    public String getTo() {
        return this.to;
    }

    public String getBody() {
        return this.body;
    }
    public String getSubject() {
        return this.subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }


}