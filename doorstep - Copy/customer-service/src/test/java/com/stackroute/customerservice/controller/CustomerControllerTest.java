package com.stackroute.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.customerservice.constants.SlotStatus;
import com.stackroute.customerservice.dto.*;
import com.stackroute.customerservice.service.IBookingDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingDetailsController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    IBookingDetailsService service;

    private List<BookingDetailsResponse> bookingDetailsResponseList;

    private BookingDetailsResponse bookingDetailsResponse;

    private Slot slot;

    @BeforeEach
    void setUp(){
        bookingDetailsResponseList=new ArrayList<>();
        bookingDetailsResponse = new BookingDetailsResponse();
        slot=new Slot();

        LocalTime strtime = LocalTime.of(7, 00, 45);
        LocalTime endtime = LocalTime.of(7, 45);

        LocalDate bookedDate = LocalDate.now();
        LocalTime bookedTime = LocalTime.now();

        slot.setSlotId("slot1");
        slot.setSlotStatus(SlotStatus.AVAILABLE);
      //  slot.setSlotDate(bookedDate);
      /*  slot.setSlotStartTime(strtime);
        slot.setSlotStartTime(endtime);*/


        bookingDetailsResponse.setCustomerEmailId("customer@email.com");
        bookingDetailsResponse.setExpertEmailId("expert@email.com");
        bookingDetailsResponse.setServiceName("service1");
        bookingDetailsResponse.setPrice(300);


        bookingDetailsResponse.setSlot(slot);

        bookingDetailsResponse.setBookingId("booking1");

        bookingDetailsResponseList.add(bookingDetailsResponse);

    }

    @Test
    void getBookingByIdTest() throws Exception {
        Mockito.when(service.getBookingById(Mockito.any())).thenReturn(bookingDetailsResponse);
        mockMvc.perform(get("/api/v1/bookings/byBookingId/booking1"))
                .andExpect(status().isOk());
    }
    @Test
    void getBookingByCustomerEmailIdTest() throws Exception {
        Mockito.when(service.getAllBookingByCustomerEmailId(Mockito.any())).thenReturn(bookingDetailsResponseList);
        mockMvc.perform(get("/api/v1/bookings/allBookingsByCustomer/customer@email.com"))
                .andExpect(status().isOk());
    }
    @Test
    void getBookingByExpertEmailIdTest() throws Exception {
        Mockito.when(service.getAllBookingByExpertEmailId(Mockito.any())).thenReturn(bookingDetailsResponseList);
        mockMvc.perform(get("/api/v1/bookings/allBookingsByExpert/expert@email.com"))
                .andExpect(status().isOk());
    }
    @Test
    void getBookingByStatusTest() throws Exception {
        Mockito.when(service.getAllBookingByStatus(Mockito.any())).thenReturn(bookingDetailsResponseList);
        mockMvc.perform(get("/api/v1/bookings/allBookingsByStatus/booked"))
                .andExpect(status().isOk());
    }

    @Test
    void getBookingByExpertEmailAndDateTest() throws Exception {
        Mockito.when(service.getAllBookingByExpertIdAndDate(Mockito.any(),Mockito.any())).thenReturn(bookingDetailsResponseList);
        mockMvc.perform(get("/api/v1/bookings/allBookings/expert@email.com/15-12-2022"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBookingTest() throws Exception{
        UpdateBookingDetails updateBookingDetails= new UpdateBookingDetails();
        updateBookingDetails.setBookingId("booking1");
        updateBookingDetails.setSlotId("slot1");
        String token="customer@email.com 31-12-2022";
        Mockito.when(service.updateBooking(Mockito.any(),Mockito.any())).thenReturn(bookingDetailsResponse);

        mockMvc
                .perform(put("/api/v1/bookings/rescheduleBooking").contentType(MediaType.APPLICATION_JSON).header("Authorization","customer@email.com 31-12-2022")
                        .content(mapper.writeValueAsString(updateBookingDetails).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isOk());



    }

    @Test
    void cancelBookingTest() throws Exception{
        CancelBooking cancelBooking= new CancelBooking();
        cancelBooking.setBookingId("booking1");
        cancelBooking.setBookingStatus("CANCELED");

        CancelBookingResponse cancelBookingResponse=new CancelBookingResponse();
        cancelBookingResponse.setMessage("Canceled Successfully");


        Mockito.when(service.cancelBooking(Mockito.any(),Mockito.any())).thenReturn(cancelBookingResponse);

        mockMvc
                .perform(put("/api/v1/bookings/cancelBooking").contentType(MediaType.APPLICATION_JSON).header("Authorization","customer@email.com 31-12-2022")
                        .content(mapper.writeValueAsString(cancelBooking).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isOk());



    }

    @Test
    void saveBookingDetailsTest() throws Exception{

        AddBookingDetails addBookingDetails=new AddBookingDetails();

        addBookingDetails.setCustomerEmailId("customer@email.com");
        addBookingDetails.setExpertEmailId("expert@email.com");
        addBookingDetails.setServiceName("service1");
        addBookingDetails.setPrice(300);
        addBookingDetails.setSlotId("slot1");
        addBookingDetails.setBookingStatus("confirmed");



        Mockito.when(service.saveBooking(Mockito.any())).thenReturn(bookingDetailsResponse);

        mockMvc
                .perform(post("/api/v1/bookings/saveBooking").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addBookingDetails).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isCreated());


    }
}
