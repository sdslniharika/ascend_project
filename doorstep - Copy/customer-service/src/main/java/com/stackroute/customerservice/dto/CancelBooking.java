package com.stackroute.customerservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public class CancelBooking {
    @Schema(example = "booking1")
    private String bookingId;

    @Schema(example = "canceled")
    private String bookingStatus;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
