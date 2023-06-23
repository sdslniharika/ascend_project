package com.stackroute.customerservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateBookingDetails {
    @Schema(example = "booking1")
    private String bookingId;
    @Schema(example = "63a4056477c99d552145c466")
    private String slotId;


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

}
