package com.stackroute.customerservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AddBookingDetails {

    @Schema(example = "userservice030@gmail.com")
    @NotBlank
    @Email
    private String customerEmailId;
    @Schema(example = "doorstp.service@gmail.com")
    @NotBlank
    @Email
    private String expertEmailId;
    @Schema(example = "Plumbing")
    private String serviceName;
    private String  slotId;
    @Schema(example = "499")
    private double price;
    @Schema(example = "confirmed")
    @NotBlank
    private String bookingStatus;

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

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
