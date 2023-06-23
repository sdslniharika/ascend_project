package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.dto.Feedbackdetails;
import com.stackroute.feedbackservice.exceptions.InvalidArgumentException;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    void addFeedback(Feedbackdetails feedbackdetails);

    void updateFeedback(Feedbackdetails feedbackdetails) throws InvalidArgumentException;

    Feedbackdetails getFeedback(String feedbackId) throws InvalidArgumentException;


    List<Feedbackdetails> getFeedbackByEmailId(String expertEmail) throws InvalidArgumentException;

    Optional<Feedbackdetails> deleteFeedback(String id);

    List<Feedbackdetails> allFeedback(String feedbackId);
}
