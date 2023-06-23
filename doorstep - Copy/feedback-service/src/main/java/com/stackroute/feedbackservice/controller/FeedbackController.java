package com.stackroute.feedbackservice.controller;

import com.stackroute.feedbackservice.dto.Feedbackdetails;
import com.stackroute.feedbackservice.exceptions.InvalidArgumentException;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/v1/feedback")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addFeedback", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addFeedback(@RequestBody Feedbackdetails feedbackdetails) {
        feedbackService.addFeedback(feedbackdetails);
        return "Sucessfully added";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/updatefeedback", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateFeedaback(@RequestBody Feedbackdetails feedbackdetails) throws InvalidArgumentException {
        feedbackService.updateFeedback(feedbackdetails);
        return "updated Sucessfully";

    }


    @GetMapping(value = "/{feedbackId}")
    public Feedbackdetails getFeedbackDetails(@PathVariable String feedbackId) throws InvalidArgumentException {

        Feedbackdetails feedbackdetails = feedbackService.getFeedback(feedbackId);
        return feedbackdetails;

    }

    @GetMapping(value = "/expertEmail")
    public List<Feedbackdetails> getDetailsByexpertEmailId(@RequestParam String expertEmail) throws InvalidArgumentException {

        List<Feedbackdetails> feedbackdetails = feedbackService.getFeedbackByEmailId(expertEmail);
        return feedbackdetails;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/id")
    public Optional<Feedbackdetails> deleteFeedback(@PathVariable String id) {
        return feedbackService.deleteFeedback(id);

    }
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/getfeedback")

    public List<Feedbackdetails> getAllFeedbacks(@PathVariable String feedbackId){
      return  feedbackService.allFeedback(feedbackId);

    }


}
