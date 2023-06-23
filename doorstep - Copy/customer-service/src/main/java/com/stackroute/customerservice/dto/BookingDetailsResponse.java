package com.stackroute.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BookingDetailsResponse {
    @Schema(example = "booking1")
    private String bookingId;
    @Schema(example = "customer@email.com")
    private String customerEmailId;
    @Schema(example = "expert@email.com")
    private String expertEmailId;
    @Schema(example = "Plumbing")
    private String serviceName;
    @Schema(example = "29/12/2022")
    private String bookingDate;
    @Schema(example = "18:30:00")
    private String bookingTime;
    private Slot slot;
    @Schema(example = "499")
    private double price;
    @Schema(example = "confirmed")
    private String bookingStatus;
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
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


    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
