package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.dto.Feedbackdetails;
import com.stackroute.feedbackservice.exceptions.InvalidArgumentException;
import com.stackroute.feedbackservice.repository.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    FeedbackDao dao;

    @Autowired
    public FeedbackServiceImpl(FeedbackDao feedbackDao) {
        this.dao = feedbackDao;


    }

    @Override
    public void addFeedback(Feedbackdetails feedbackdetails) {
        dao.save(feedbackdetails);

    }


    @Override
    public void updateFeedback(Feedbackdetails updatedfeedbackdetails) throws InvalidArgumentException {
        Optional<Feedbackdetails> feedbackdetails1 = dao.findById(String.valueOf(updatedfeedbackdetails.getId()));
        if (feedbackdetails1.isPresent()) {

            feedbackdetails1.get().setId(updatedfeedbackdetails.getId());
            feedbackdetails1.get().setServiceName(updatedfeedbackdetails.getServiceName());
            feedbackdetails1.get().setBookingId(updatedfeedbackdetails.getBookingId());
            feedbackdetails1.get().setCustomerEmail(updatedfeedbackdetails.getCustomerEmail());
            feedbackdetails1.get().setExpertEmail(updatedfeedbackdetails.getExpertEmail());
            feedbackdetails1.get().setRating(updatedfeedbackdetails.getRating());
            feedbackdetails1.get().setReviewComments(updatedfeedbackdetails.getReviewComments());
            dao.save(feedbackdetails1.get());
        } else {
            throw new InvalidArgumentException("Invalid FeedbackId");
        }


    }


    @Override
    public Feedbackdetails getFeedback(String feedbackId) throws InvalidArgumentException {
        Optional<Feedbackdetails> feedbackdetails = dao.findById(String.valueOf(feedbackId));
        return feedbackdetails.orElseThrow(() -> new InvalidArgumentException("Invalid FeedbackId"));


    }

    @Override
    public List<Feedbackdetails> getFeedbackByEmailId(String expertEmail) throws InvalidArgumentException {
        Optional<List<Feedbackdetails>> feedbackdetails = dao.findByExpertEmail(expertEmail);

        return feedbackdetails.orElseThrow(() -> new InvalidArgumentException("Invalid FeedbackId"));


    }

    @Override
    public Optional<Feedbackdetails> deleteFeedback(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Feedbackdetails> allFeedback(String feedbackId) {
        return null;
    }
}
