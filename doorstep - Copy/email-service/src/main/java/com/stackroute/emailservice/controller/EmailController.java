package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.model.EmailDetails;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/emails")
@Validated
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendMail(@Valid @RequestBody EmailDetails details) throws MessagingException {
    emailService.createMail(details);
        return "mail send successfully";
    }
    @PostMapping("/rescheduleEmail")
    public String rescheduleEmail(@Valid @RequestBody EmailDetails details) throws MessagingException {

        emailService.rescheduleEmail(details);
        return "rescheduled successfully";
    }
    @PostMapping("/cancelEmail")
    public String cancelEmail(@Valid @RequestBody EmailDetails details) throws MessagingException {
        emailService.cancelEmail(details);
        return "canceled successfully";
    }
}
