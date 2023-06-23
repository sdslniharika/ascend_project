package com.stackroute.emailservice.config;

import com.stackroute.emailservice.model.BookingResponse;
import com.stackroute.emailservice.model.EmailDetails;
import com.stackroute.emailservice.service.EmailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class Consumer {
    @Autowired
    private EmailServiceImpl emailService;

    @RabbitListener(queues="bookingdeatils_queue")
    public void getUserDtoFromRabbitMq(BookingResponse bookingResponse) throws MessagingException {
        EmailDetails emailDetails= new EmailDetails();
        emailDetails.setCustomerEmail(bookingResponse.getCustomerEmailId());
        emailDetails.setExpertEmail(bookingResponse.getExpertEmailId());
        emailDetails.setBookingId(bookingResponse.getBookingId());
        emailDetails.setSlot(bookingResponse.getSlot());
        emailDetails.setBookingDate(bookingResponse.getBookingDate());
        emailDetails.setBookingTime(bookingResponse.getBookingTime());
        emailDetails.setBookingStatus(bookingResponse.getBookingStatus());
        emailDetails.setServiceName(bookingResponse.getServiceName());
        emailDetails.setPrice(bookingResponse.getPrice());
        if(bookingResponse.getBookingStatus().equalsIgnoreCase("CONFIRMED")){
            emailService.createMail(emailDetails);
        }
        else if(bookingResponse.getBookingStatus().equalsIgnoreCase("RESCHEDULED")){
            emailService.rescheduleEmail(emailDetails);
        }
        else if(bookingResponse.getBookingStatus().equalsIgnoreCase("CANCELED")){
            emailService.cancelEmail(emailDetails);
        }


    }
}