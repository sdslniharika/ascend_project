package com.stackroute.emailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private String bookingId;
    private  String customerEmailId;
    private String expertEmailId;
    private String serviceName;
    private String bookingDate;
    private String bookingTime;
    private Slot slot;
    private String price;
    private String bookingStatus;
}
