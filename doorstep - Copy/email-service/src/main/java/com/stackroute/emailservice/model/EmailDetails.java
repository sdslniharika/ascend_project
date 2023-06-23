package com.stackroute.emailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    @NotBlank(message = "bookingId can not be blank")
    private String bookingId;
    @NotBlank(message = "customerEmail can not be blank")
    private  String customerEmail;
    @NotBlank(message = "expertEmail can not be blank")
    private String expertEmail;
    @Valid
    private Slot slot;
    private String bookingDate;
    private String bookingTime;
    @NotBlank(message = "bookingStatus can not be blank")
    private String bookingStatus;
    @NotBlank(message = " price can not be blank")
    private String price;
    @NotBlank(message = "serviceName can not be blank")
    private String serviceName;

}
