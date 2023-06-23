package com.stackroute.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CancelBookingResponse {
@Schema(example = "Canceled Successfully")
    String message;

    Slot slot;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
