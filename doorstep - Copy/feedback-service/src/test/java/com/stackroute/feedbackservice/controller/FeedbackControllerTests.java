package com.stackroute.feedbackservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.feedbackservice.dto.Feedbackdetails;
import com.stackroute.feedbackservice.repository.FeedbackDao;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FeedbackControllerTests {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private FeedbackService feedbackService;
    @MockBean
    private FeedbackDao dao;

    @Test
    void test_AddFeedback_addedSucessfully() throws Exception {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("good");
        feedbackdetails.setRating(5);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setCustomerEmail("venkat@gmail.com");
        Mockito.when(dao.save(Mockito.any(Feedbackdetails.class))).thenReturn(feedbackdetails);
        this.mockmvc.perform(post("/api/v1/feedback/addFeedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(feedbackdetails)))
                .andExpect(status().isCreated());


    }

    @Test
    void test_updateFeedback_updatedSucessfully() throws Exception {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("bad");
        feedbackdetails.setRating(2);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setCustomerEmail("venkat@gmail.com");
        Mockito.when(dao.save(Mockito.any(Feedbackdetails.class))).thenReturn(feedbackdetails);
        this.mockmvc.perform(put("/api/v1/feedback/updatefeedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(feedbackdetails)))
                .andExpect(status().isAccepted());


    }

    @Test
    void test_getFeedbackDetails_returnedSucessfully() throws Exception {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setId("1234");
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("bad");
        feedbackdetails.setRating(2);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setCustomerEmail("venkat@gmail.com");
        Mockito.when(dao.findById(Mockito.any(String.class))).thenReturn(Optional.of(feedbackdetails));
        this.mockmvc.perform(get("/api/v1/feedback/1234")
                        .contentType(MediaType.APPLICATION_JSON))
                //  .content(mapper.writeValueAsString("1234")))
                .andExpect(status().isOk());


    }

    @Test
    void test_getDetailsByExpertEmailId_returnedSucessfully() throws Exception {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setId("1234");
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("bad");
        feedbackdetails.setRating(2);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setCustomerEmail("venkat@gmail.com");
        List<Feedbackdetails> list = new ArrayList<>();
        list.add(feedbackdetails);
        Mockito.when(dao.findByExpertEmail(Mockito.any(String.class))).thenReturn((Optional.of(list)));
        this.mockmvc.perform(get("/api/v1/feedback/expertEmail?expertEmail=plumber@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("1234")))
                .andExpect(status().isOk());


    }


}
