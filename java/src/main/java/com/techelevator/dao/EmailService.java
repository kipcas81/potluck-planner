package com.techelevator.dao;

import com.techelevator.model.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);

}
