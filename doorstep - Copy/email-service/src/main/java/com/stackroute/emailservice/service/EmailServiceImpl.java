package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void createMail(EmailDetails emailDetails) throws MessagingException {
        sendEmailToCustomer(emailDetails, "Order Successfully Placed", "ordered");
        sendEmailToExpert(emailDetails, "Order Successfully Placed", "ordered");

    }

    @Override
    public void rescheduleEmail(EmailDetails emailDetails) throws MessagingException {
        sendEmailToCustomer(emailDetails, "Order Successfully Rescheduled", "rescheduled");
        sendEmailToExpert(emailDetails, "Order Successfully Rescheduled", "rescheduled");
    }

    @Override
    public void cancelEmail(EmailDetails emailDetails) throws MessagingException {
        sendEmailToCustomer(emailDetails, "Order Successfully Cancelled", "cancelled");
        sendEmailToExpert(emailDetails, "Order Successfully Cancelled", "cancelled");
    }


    private void sendEmailToCustomer(EmailDetails emailDetails, String subject, String type) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(subject);
        helper.setFrom("doorstp.service@gmail.com");
        helper.setText(getCustomerMessage(emailDetails, type), true);
        helper.setTo(emailDetails.getCustomerEmail());
        javaMailSender.send(message);
    }

    private void sendEmailToExpert(EmailDetails emailDetails, String subject,String type) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(subject);
        helper.setFrom("doorstp.service@gmail.com");
        helper.setText(getExpertMessage(emailDetails, type), true);
        helper.setTo(emailDetails.getExpertEmail());
        javaMailSender.send(message);
    }

    private String getCustomerMessage(EmailDetails emailDetails, String type) {
        String sBuilder = "<p>Hi " + emailDetails.getCustomerEmail() + ",</p>" +
                "<p>&nbsp;</p>" +
                "<p>You have " + type + " the "+emailDetails.getServiceName()+" service successfully for the time slot <strong>" + emailDetails.getSlot().getSlotStartTime() + "-" + emailDetails.getSlot().getSlotEndTime() + "</strong> on <strong>"+ emailDetails.getSlot().getSlotDate()+"</strong>.</p>" +
                "<p>&nbsp;</p>" +
                "<p>Booking Reference No: " + emailDetails.getBookingId() + "</p>" +
                "<p>Service Cost: " +emailDetails.getPrice() + "</p>" +
                "<p>Booked Status: " +emailDetails.getBookingStatus() + "</p>" +
                "<p>Date: " + LocalDate.now() + "</p>" +
                "<p>Time: " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES)+ " </p>" +
                "<p>In case of any query please write us at  doorstp.service@gmail.com  </p> " +
                "<p>&nbsp;</p>" +


                "<p>Thank you for visiting DoorStep.</p>";
        return sBuilder;
    }

    private String getExpertMessage(EmailDetails emailDetails, String type) {
        String sBuilder = "<p>Hi " + emailDetails.getExpertEmail() + ",</p>" +
                "<p>&nbsp;</p>" +
                "<p>Your "+emailDetails.getServiceName()+" service has been " + type + " successfully for the time slot <strong>" + emailDetails.getSlot().getSlotStartTime() + "-" + emailDetails.getSlot().getSlotEndTime() + "</strong> on <strong>" + emailDetails.getSlot().getSlotDate()+"</strong>.</p>" +
                "<p>&nbsp;</p>" +
                "<p>Booking Reference No: " + emailDetails.getBookingId() + "</p>" +
                "<p>Service Cost: " +emailDetails.getPrice() + "</p>" +
                "<p>Booked Status: " +emailDetails.getBookingStatus() + "</p>" +
                "<p>Date: " + LocalDate.now() + "</p>" +
                "<p>Time: " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES)+ " </p>" +
                "<p>In case of any query please write us at  doorstp.service@gmail.com  </p> " +
                "<p>&nbsp;</p>" +


                "<p>Thank you for visiting DoorStep.</p>";
        return sBuilder;
    }


}