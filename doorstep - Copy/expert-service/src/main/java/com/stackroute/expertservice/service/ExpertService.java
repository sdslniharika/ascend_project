package com.stackroute.expertservice.service;

import com.stackroute.expertservice.constants.ExpertConstants;
import com.stackroute.expertservice.dto.AddExpertAvailability;
import com.stackroute.expertservice.dto.AddSlotToExpertAvailability;
import com.stackroute.expertservice.dto.AddAvailableDateToExpertAvailability;
import com.stackroute.expertservice.dto.UpdateSlot;
import com.stackroute.expertservice.dto.UpdateSlotStatus;
import com.stackroute.expertservice.dto.SlotDetails;
import com.stackroute.expertservice.entity.AvailableDate;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.Slot;
import com.stackroute.expertservice.enums.SlotStatus;
import com.stackroute.expertservice.exception.ExpertAvailabilityNotFoundException;
import com.stackroute.expertservice.exception.InvalidArgumentException;
import com.stackroute.expertservice.exception.SlotNotFoundException;
import com.stackroute.expertservice.mapper.ExpertMapper;
import com.stackroute.expertservice.repository.ExpertRepository;
import com.stackroute.expertservice.utils.ExpertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpertService implements IExpertService {

    private final ExpertRepository expertRepository;

    private final ExpertMapper expertMapper;

    @Autowired
    public ExpertService(ExpertRepository expertRepository, ExpertMapper expertMapper) {
        this.expertRepository = expertRepository;
        this.expertMapper = expertMapper;
    }

    @Override
    public List<ExpertAvailability> getAllAvailableExperts(Optional<Boolean> includeNotAvailable) {
        List<ExpertAvailability> availableExperts = expertRepository.findByAvailableDates_Slots_SlotStatusAndAvailableDates_SlotDateGreaterThanEqual(SlotStatus.AVAILABLE, LocalDate.now());
        if (includeNotAvailable.isPresent() && includeNotAvailable.get().equals(true)) {
            return availableExperts;
        }
        for (int index = 0; index < availableExperts.size(); index++) {
            ExpertAvailability expertAvailability = availableExperts.get(index);
            List<AvailableDate> availableDates = new ArrayList<>();
            for (AvailableDate availableDate : expertAvailability.getAvailableDates()) {
                Set<Slot> slots = availableDate.getSlots().stream().filter(s -> s.getSlotStatus().equals(SlotStatus.AVAILABLE)).collect(Collectors.toSet());
                AvailableDate availableDateTemp = new AvailableDate();
                if (!slots.isEmpty()) {
                    availableDateTemp.setSlotDate(availableDate.getSlotDate());
                    availableDateTemp.setSlots(slots);
                }
                availableDates.add(availableDateTemp);
            }
            expertAvailability.setAvailableDates(availableDates);
        }
        return availableExperts;
    }

    @Override
    public List<ExpertAvailability> getAvailableExperts(String serviceName, Optional<String> expertEmail) {
        if (expertEmail.isPresent()) {
            return expertRepository.findByExpertEmailAndServiceNameAndAvailableDates_SlotDateGreaterThanEqual(expertEmail.get(), serviceName, LocalDate.now());
        }
        return expertRepository.findByServiceNameAndAvailableDates_SlotDateGreaterThanEqual(serviceName, LocalDate.now());
    }

    @Override
    public ExpertAvailability addAvailability(AddExpertAvailability expertAvailabilityDetails) throws InvalidArgumentException {
        ExpertUtils.validateToken(expertAvailabilityDetails.getExpertEmail());
        ExpertUtils.isExpert();
        List<ExpertAvailability> expertAvailabilityList = expertRepository.findByExpertEmailAndServiceNameAndAvailableDates_SlotDateGreaterThanEqual(expertAvailabilityDetails.getExpertEmail(), expertAvailabilityDetails.getServiceName(), LocalDate.now());
        if (!expertAvailabilityList.isEmpty()) {
            throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.ALREADY_EXIST);
        }
        return expertRepository.save(expertMapper.mapToExpertAvailability(expertAvailabilityDetails));
    }

    @Override
    public ExpertAvailability addSlots(AddSlotToExpertAvailability addSlotToExpertAvailability) throws InvalidArgumentException, ExpertAvailabilityNotFoundException {
        ExpertUtils.validateToken(addSlotToExpertAvailability.getExpertEmail());
        ExpertUtils.isExpert();
        List<ExpertAvailability> expertAvailabilityList = getAvailableExperts(addSlotToExpertAvailability.getServiceName(), Optional.of(addSlotToExpertAvailability.getExpertEmail()));
        if (expertAvailabilityList.isEmpty()) {
            throw new ExpertAvailabilityNotFoundException(ExpertConstants.EXPERT_AVAILABILITY_NOT_FOUND + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.COLON + addSlotToExpertAvailability.getServiceName());
        }
        ExpertAvailability expertAvailability = expertAvailabilityList.get(0);
        LocalDate localSlotDate = LocalDate.parse(addSlotToExpertAvailability.getSlotDate());
        List<AvailableDate> availableDateList = expertAvailability.getAvailableDates();
        for (int index = 0; index < availableDateList.size(); index++) {
            AvailableDate date = availableDateList.get(index);
            if (date.getSlotDate().isEqual(localSlotDate)) {
                date.getSlots().addAll(expertMapper.mapToSlots(addSlotToExpertAvailability.getSlots(), addSlotToExpertAvailability.getSlotDate()));
                return expertRepository.save(expertAvailability);
            }
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setSlotDate(localSlotDate);
        availableDate.setSlots(new HashSet<>(expertMapper.mapToSlots(addSlotToExpertAvailability.getSlots(), addSlotToExpertAvailability.getSlotDate())));
        availableDateList.add(availableDate);
        return expertRepository.save(expertAvailability);
    }

    @Override
    public ExpertAvailability addAvailableDate(AddAvailableDateToExpertAvailability addAvailableDateToExpertAvailability) throws InvalidArgumentException, ExpertAvailabilityNotFoundException {
        ExpertUtils.validateToken(addAvailableDateToExpertAvailability.getExpertEmail());
        ExpertUtils.isExpert();
        List<ExpertAvailability> expertAvailabilityList = getAvailableExperts(addAvailableDateToExpertAvailability.getServiceName(), Optional.of(addAvailableDateToExpertAvailability.getExpertEmail()));
        if (expertAvailabilityList.isEmpty()) {
            throw new ExpertAvailabilityNotFoundException(ExpertConstants.EXPERT_AVAILABILITY_NOT_FOUND + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.COLON + addAvailableDateToExpertAvailability.getServiceName());
        }
        ExpertAvailability expertAvailability = expertAvailabilityList.get(0);
        List<AvailableDate> availableDateList = expertAvailability.getAvailableDates();
        for (AvailableDate availableDate : availableDateList) {
            if (availableDate.getSlotDate().toString().equals(addAvailableDateToExpertAvailability.getAvailableDate().getSlotDate())) {
                throw new InvalidArgumentException(ExpertConstants.AVAILABLE_DATE_ALREADY_EXIST + ExpertConstants.PARAM_AVAILABLE_DATE + ExpertConstants.COLON + availableDate.getSlotDate());
            }
        }
        availableDateList.add(expertMapper.mapToAvailableDate(addAvailableDateToExpertAvailability.getAvailableDate()));
        return expertRepository.save(expertAvailability);
    }

    private List<ExpertAvailability> getAllServices() {
        return expertRepository.findAll();
    }


    @Override
    public SlotDetails updateSlot(UpdateSlot updateSlot) throws InvalidArgumentException, SlotNotFoundException {
        return updateSlotData(updateSlot, updateSlot.getSlotId());
    }

    @Override
    public SlotDetails updateSlotStatus(UpdateSlotStatus updateSlotStatus, Optional<String> oldSlotId) throws InvalidArgumentException, SlotNotFoundException {
        if (oldSlotId.isPresent() && !oldSlotId.get().equals(updateSlotStatus.getSlotId())) {
            UpdateSlotStatus oldSlot = new UpdateSlotStatus();
            oldSlot.setSlotId(oldSlotId.get());
            oldSlot.setSlotStatus(SlotStatus.AVAILABLE.name());
            updateSlotData(oldSlot, oldSlotId.get());
        }
        return updateSlotData(updateSlotStatus, updateSlotStatus.getSlotId());

    }

    @Override
    public String deleteSlot(String slotId) throws SlotNotFoundException {
        for (ExpertAvailability expertAvailability : getAllServices()) {
            List<AvailableDate> availableDates = expertAvailability.getAvailableDates();
            int index = 0;
            while (index < availableDates.size()) {
                Set<Slot> slots = availableDates.get(index).getSlots();
                if (slots.removeIf(s -> {
                    boolean isEqual = s.getSlotId().equals(slotId);
                    if (isEqual) {
                        ExpertUtils.validateToken(expertAvailability.getExpertEmail());
                        ExpertUtils.isExpert();
                    }
                    return isEqual;
                })) {
                    if (slots.isEmpty()) {
                        availableDates.remove(index);
                    }
                    expertRepository.save(expertAvailability);
                    return slotId;
                }
            }
        }
        throw new SlotNotFoundException(ExpertConstants.SLOT_NOT_FOUND + ExpertConstants.PARAM_SLOT_ID + ExpertConstants.COLON + slotId);
    }

    @Override
    public String deleteService(String expertEmail, Optional<String> serviceName) throws SlotNotFoundException, ExpertAvailabilityNotFoundException {
        ExpertUtils.validateToken(expertEmail);
        ExpertUtils.isExpert();
        if (serviceName.isEmpty()) {
            if (expertRepository.deleteByExpertEmail(expertEmail).equals(ExpertConstants.NUM_0)) {
                throw new ExpertAvailabilityNotFoundException(ExpertConstants.EXPERT_AVAILABILITY_NOT_FOUND + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.COLON + expertEmail);
            } else {
                return expertEmail;
            }
        }
        if (expertRepository.deleteByExpertEmailAndServiceName(expertEmail, serviceName.get()).equals(ExpertConstants.NUM_0)) {
            throw new SlotNotFoundException(ExpertConstants.SERVICE_NOT_FOUND + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.COLON + serviceName.get());
        }
        return serviceName.get();
    }

    private SlotDetails updateSlotData(Object updateSlotData, String slotId) throws InvalidArgumentException, SlotNotFoundException {
        for (ExpertAvailability expertAvailability : getAllServices()) {
            for (AvailableDate availableDate : expertAvailability.getAvailableDates()) {
                for (Slot slot : availableDate.getSlots()) {
                    if (saveSlot(updateSlotData, slotId, slot, expertAvailability)) {
                        return expertMapper.mapToSlotDetails(slot, availableDate.getSlotDate().toString());
                    }
                }
            }
        }
        throw new SlotNotFoundException(ExpertConstants.SLOT_NOT_FOUND + ExpertConstants.PARAM_SLOT_ID + ExpertConstants.COLON + slotId);

    }

    private boolean saveSlot(Object updateSlotData, String slotId, Slot slot, ExpertAvailability expertAvailability) throws InvalidArgumentException {
        if (updateSlotData instanceof UpdateSlot) {
            UpdateSlot updateSlot = (UpdateSlot) updateSlotData;
            if (slot.getSlotId().equals(slotId)) {
                ExpertUtils.validateToken(expertAvailability.getExpertEmail());
                ExpertUtils.isExpert();
                expertMapper.mapToSlot(updateSlot, slot);
                expertRepository.save(expertAvailability);
                return true;
            }
        }
        if (updateSlotData instanceof UpdateSlotStatus) {
            UpdateSlotStatus updateSlotStatus = (UpdateSlotStatus) updateSlotData;
            if (slot.getSlotId().equals(slotId)) {
                slot.setSlotStatus(ExpertUtils.getSlotStatus(updateSlotStatus.getSlotStatus()));
                expertRepository.save(expertAvailability);
                return true;
            }
        }
        return false;
    }
}
