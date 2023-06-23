package com.stackroute.customerservice.documents;

import com.stackroute.customerservice.constants.BookingStatus;
import com.stackroute.customerservice.dto.Slot;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalTime;

@Document("booking_details")
public class BookingDetails {

    @Id
    private String bookingId;
    @Email
    private String customerEmailId;
    @Email
    private String expertEmailId;
    private String serviceName;

    private LocalDate bookingDate;

    private LocalTime bookingTime;
    private Slot slot;

    private double price;

    private BookingStatus bookingStatus;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getExpertEmailId() {
        return expertEmailId;
    }

    public void setExpertEmailId(String expertEmailId) {
        this.expertEmailId = expertEmailId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
