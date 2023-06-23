package com.stackroute.feedbackservice.repository;

import com.stackroute.feedbackservice.dto.Feedbackdetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackDao extends MongoRepository<Feedbackdetails, String> {

    Optional<List<Feedbackdetails>> findByExpertEmail(String expertEmail);
}
