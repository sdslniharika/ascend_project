package com.stackroute.customerservice.repository;

import com.stackroute.customerservice.constants.BookingStatus;
import com.stackroute.customerservice.documents.BookingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDetailsRepository extends MongoRepository<BookingDetails,String> {

    List<BookingDetails> findByCustomerEmailId(String customerEmailId);

    List<BookingDetails> findByExpertEmailId(String expertEmailId);

    List<BookingDetails> findByBookingStatus(BookingStatus bookingStatus);

    List<BookingDetails> findByExpertEmailIdAndBookingDate(String expertEmailId, LocalDate date);
}
