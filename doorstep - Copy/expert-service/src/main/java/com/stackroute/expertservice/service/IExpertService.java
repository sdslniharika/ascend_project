package com.stackroute.expertservice.service;

import com.stackroute.expertservice.dto.AddExpertAvailability;
import com.stackroute.expertservice.dto.AddSlotToExpertAvailability;
import com.stackroute.expertservice.dto.AddAvailableDateToExpertAvailability;
import com.stackroute.expertservice.dto.UpdateSlot;
import com.stackroute.expertservice.dto.UpdateSlotStatus;
import com.stackroute.expertservice.dto.SlotDetails;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.exception.ExpertAvailabilityNotFoundException;
import com.stackroute.expertservice.exception.InvalidArgumentException;
import com.stackroute.expertservice.exception.SlotNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface IExpertService {

    List<ExpertAvailability> getAllAvailableExperts(Optional<Boolean> includeNotAvailable);

    List<ExpertAvailability> getAvailableExperts(String serviceName, Optional<String> expertEmail);

    ExpertAvailability addAvailability(@Valid AddExpertAvailability expertAvailabilityDetails) throws InvalidArgumentException;

    ExpertAvailability addSlots(@Valid AddSlotToExpertAvailability addSlotToExpertAvailability) throws InvalidArgumentException, ExpertAvailabilityNotFoundException;

    ExpertAvailability addAvailableDate(@Valid AddAvailableDateToExpertAvailability addAvailableDateToExpertAvailability) throws InvalidArgumentException, ExpertAvailabilityNotFoundException;

    SlotDetails updateSlot(@Valid UpdateSlot updateSlot) throws InvalidArgumentException, SlotNotFoundException;

    SlotDetails updateSlotStatus(@Valid UpdateSlotStatus updateSlotStatus, Optional<String> oldSlotId) throws InvalidArgumentException, SlotNotFoundException;

    String deleteSlot(String slotId) throws SlotNotFoundException;

    String deleteService(String expertEmail, Optional<String> serviceName) throws SlotNotFoundException, ExpertAvailabilityNotFoundException;


}
