package com.stackroute.expertservice.mapper;

import com.stackroute.expertservice.constants.ExpertConstants;
import com.stackroute.expertservice.dto.AddExpertAvailability;
import com.stackroute.expertservice.dto.AddAvailableDate;
import com.stackroute.expertservice.dto.UpdateSlot;
import com.stackroute.expertservice.dto.AddSlot;
import com.stackroute.expertservice.dto.SlotDetails;
import com.stackroute.expertservice.entity.AvailableDate;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.Slot;
import com.stackroute.expertservice.exception.InvalidArgumentException;
import com.stackroute.expertservice.utils.ExpertUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExpertMapper {


    public ExpertAvailability mapToExpertAvailability(AddExpertAvailability expertAvailabilityDetails) throws InvalidArgumentException {
        ExpertAvailability expertAvailability = new ExpertAvailability();
        expertAvailability.setExpertEmail(expertAvailabilityDetails.getExpertEmail().trim());
        expertAvailability.setPrice(expertAvailabilityDetails.getPrice());
        expertAvailability.setServiceName(expertAvailabilityDetails.getServiceName().trim());
        expertAvailability.setAvailableDates(mapToAvailableDates(expertAvailabilityDetails.getAvailableDates()));
        return expertAvailability;
    }

    private List<AvailableDate> mapToAvailableDates(List<AddAvailableDate> addAvailableDates) throws InvalidArgumentException {
        List<AvailableDate> availableDates = new ArrayList<>();
        for (AddAvailableDate addAvailableDate : addAvailableDates) {
            availableDates.add(mapToAvailableDate(addAvailableDate));
        }
        return availableDates;
    }

    public AvailableDate mapToAvailableDate(AddAvailableDate addAvailableDate) throws InvalidArgumentException {
        AvailableDate availableDate = new AvailableDate();
        availableDate.setSlots(mapToSlots(addAvailableDate.getSlots(), addAvailableDate.getSlotDate()));
        availableDate.setSlotDate(LocalDate.parse(addAvailableDate.getSlotDate()));
        return availableDate;
    }

    public Slot mapToSlot(AddSlot slotDetails, String availableDate) throws InvalidArgumentException {
        LocalDate localDate = LocalDate.parse(availableDate);
        if (localDate.isBefore(LocalDate.now())) {
            throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_DATE + ExpertConstants.DATE_CANT_BE_PAST);
        }
        Slot slot = new Slot();
        slot.setSlotId(ObjectId.get().toString());
        LocalTime localDateStart = LocalTime.parse(slotDetails.getSlotStartTime());
        LocalTime localDateEnd = LocalTime.parse(slotDetails.getSlotEndTime());
        if (localDate.isEqual(LocalDate.now())) {
            if (localDateStart.isBefore(LocalTime.now())) {
                throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_START_TIME + ExpertConstants.TIME_CANT_BE_PAST);
            }
            if (localDateEnd.isBefore(LocalTime.now())) {
                throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_END_TIME + ExpertConstants.TIME_CANT_BE_PAST);
            }
        }
        slot.setSlotStartTime(localDateStart);
        slot.setSlotStartTime(localDateStart);
        slot.setSlotEndTime(localDateEnd);
        slot.setSlotStatus(ExpertUtils.getSlotStatus(slotDetails.getSlotStatus()));
        return slot;
    }

    public Set<Slot> mapToSlots(Set<AddSlot> addSlots, String availableDate) throws InvalidArgumentException {
        Set<Slot> slots = new HashSet<>();
        for (AddSlot addSlot : addSlots) {
            slots.add(mapToSlot(addSlot, availableDate));
        }
        return slots;
    }

    public void mapToSlot(UpdateSlot updateSlot, Slot slot) throws InvalidArgumentException {
        LocalTime localTimeStart = LocalTime.parse(updateSlot.getSlotStartTime().trim());
        LocalTime localTimeEnd = LocalTime.parse(updateSlot.getSlotEndTime().trim());
        validateSlotTiming(localTimeStart, localTimeEnd);
        slot.setSlotStartTime(localTimeStart);
        slot.setSlotEndTime(localTimeEnd);
    }

    private void validateSlotTiming(LocalTime slotStartTime, LocalTime slotEndTime) throws InvalidArgumentException {
        if (slotStartTime.isBefore(LocalTime.now())) {
            throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_START_TIME + ExpertConstants.TIME_CANT_BE_PAST);
        }
        if (slotEndTime.isBefore(LocalTime.now()) || slotEndTime.equals(slotStartTime)) {
            throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_END_TIME + ExpertConstants.TIME_CANT_BE_PAST_OR_EQUAL + ExpertConstants.PARAM_SLOT_START_TIME);
        }
    }


    public SlotDetails mapToSlotDetails(Slot slot, String slotDate) {
        SlotDetails slotDetails = new SlotDetails();
        slotDetails.setSlotId(slot.getSlotId());
        slotDetails.setSlotDate(slotDate);
        slotDetails.setSlotStartTime(slot.getSlotStartTime());
        slotDetails.setSlotEndTime(slot.getSlotEndTime());
        slotDetails.setSlotStatus(slot.getSlotStatus());
        return slotDetails;
    }
}
