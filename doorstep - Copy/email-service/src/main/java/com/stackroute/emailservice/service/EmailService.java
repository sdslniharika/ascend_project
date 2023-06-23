package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.EmailDetails;

import javax.mail.MessagingException;


public interface EmailService {
    void createMail(EmailDetails details) throws MessagingException;

    void rescheduleEmail(EmailDetails emailDetails) throws MessagingException;
    void cancelEmail(EmailDetails emailDetails) throws MessagingException;

}
