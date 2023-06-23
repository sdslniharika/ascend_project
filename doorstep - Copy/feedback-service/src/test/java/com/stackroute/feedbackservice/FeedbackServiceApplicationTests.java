package com.stackroute.feedbackservice;

import com.stackroute.feedbackservice.dto.Feedbackdetails;
import com.stackroute.feedbackservice.exceptions.InvalidArgumentException;
import com.stackroute.feedbackservice.repository.FeedbackDao;
import com.stackroute.feedbackservice.service.FeedbackService;
import com.stackroute.feedbackservice.service.FeedbackServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceApplicationTests {
    @Autowired
    FeedbackService feedbackService;
    @Mock
    FeedbackDao dao;

    @BeforeEach
    public void before() {
        feedbackService = new FeedbackServiceImpl(dao);


    }

    @Test
    void test_AddFeedback_ShouldSaveSucessfully() {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("good");
        feedbackdetails.setRating(5);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setCustomerEmail("venkat@gmail.com");
        feedbackService.addFeedback(feedbackdetails);


    }

    @Test
    void test_getFeedbackDetails_recivedDetailsSucessfully() throws InvalidArgumentException {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("good");
        feedbackdetails.setRating(5);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setId("ab253v");
        feedbackdetails.setBookingId("101");
        Mockito.when(dao.findById(Mockito.any(String.class))).thenReturn(Optional.of(feedbackdetails));
        Feedbackdetails details = feedbackService.getFeedback("ab253v");
        Assertions.assertTrue(details.getBookingId().equals(feedbackdetails.getBookingId()));
        Assertions.assertTrue(details.getServiceName().equals(feedbackdetails.getServiceName()));
    }

    @Test
    void test_getFeedbackDetailsByEmailId_returnsDataSucessfully() throws InvalidArgumentException {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("good");
        feedbackdetails.setRating(5);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setId("ab253v");
        feedbackdetails.setBookingId("101");
        List<Feedbackdetails> feedbackdetailsList = new ArrayList<>();
        feedbackdetailsList.add(feedbackdetails);
        Mockito.when(dao.findByExpertEmail(Mockito.any(String.class))).thenReturn(Optional.of(feedbackdetailsList));
        List<Feedbackdetails> feedbackdetailsList1 = feedbackService.getFeedbackByEmailId("plumber@gmail.com");
        Assertions.assertTrue(feedbackdetailsList1.size() > 0);
        Assertions.assertTrue(feedbackdetailsList1.get(0).getBookingId().equals(feedbackdetails.getBookingId()));

    }

    @Test
    void test_updateFeedbackDetails_UpdatedSucessfully() throws InvalidArgumentException {
        Feedbackdetails feedbackdetails = new Feedbackdetails();
        feedbackdetails.setServiceName("plumber");
        feedbackdetails.setReviewComments("good");
        feedbackdetails.setRating(5);
        feedbackdetails.setExpertEmail("plumber@gmail.com");
        feedbackdetails.setCustomerEmail("venkat@gmail.com");
        feedbackdetails.setId("123");
        Mockito.when(dao.findById("123")).thenReturn(Optional.of(feedbackdetails));
        feedbackService.updateFeedback(feedbackdetails);

    }
}