package com.stackroute.feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "Feedback_details")
public class Feedbackdetails {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String bookingId;

    @Schema(example = "userservice030@gmail.com")
    private String customerEmail;
    @Schema(example = "doorstp.service@gmail.com")
    private String expertEmail;
    @Schema(example = "4")
    private Integer rating;
    @Schema(example = "Great Service")
    private String reviewComments;

    @Schema(example = "Plumbing")
    private String serviceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getExpertEmail() {
        return expertEmail;
    }

    public void setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedbackdetails{" +
                "Id='" + id + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", expertEmail='" + expertEmail + '\'' +
                ", rating=" + rating +
                ", reviewComments='" + reviewComments + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

