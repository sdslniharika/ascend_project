package com.stackroute.expertservice.repository;

import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.enums.SlotStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpertRepository extends MongoRepository<ExpertAvailability, String> {

    List<ExpertAvailability> findByServiceNameAndAvailableDates_SlotDateGreaterThanEqual(String serviceId, LocalDate now);

    List<ExpertAvailability> findByAvailableDates_Slots_SlotStatusAndAvailableDates_SlotDateGreaterThanEqual(SlotStatus slotStatus, LocalDate now);

    List<ExpertAvailability> findByExpertEmailAndServiceNameAndAvailableDates_SlotDateGreaterThanEqual(String expertEmail, String serviceName, LocalDate now);

    String deleteByExpertEmail(String expertEmail);

    String deleteByExpertEmailAndServiceName(String expertEmail, String serviceName);
}
