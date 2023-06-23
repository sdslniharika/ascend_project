package com.stackroute.expertservice.controller;

import com.stackroute.expertservice.constants.ExpertConstants;
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
import com.stackroute.expertservice.service.IExpertService;
import com.stackroute.expertservice.utils.ExpertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/v1/availability")
@RestController
public class ExpertController {

    private final IExpertService expertService;
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Autowired
    public ExpertController(IExpertService expertService) {
        this.expertService = expertService;
    }

    @GetMapping(value = "/allAvailableExperts")
    public List<ExpertAvailability> getAllAvailableExperts(@RequestParam Optional<Boolean> includeNotAvailable) {
        return expertService.getAllAvailableExperts(includeNotAvailable);
    }

    @GetMapping(value = "/availableExperts")
    public List<ExpertAvailability> getAvailableExpertsByServiceId(@RequestParam String serviceName, @RequestParam Optional<String> expertEmail) {
        return expertService.getAvailableExperts(serviceName, expertEmail);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/saveAvailability")
    public ExpertAvailability addAvailability(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody AddExpertAvailability expertAvailabilityDetails) throws InvalidArgumentException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return expertService.addAvailability(expertAvailabilityDetails);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/addSlots")
    public ExpertAvailability addSlots(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody AddSlotToExpertAvailability addSlots) throws InvalidArgumentException, ExpertAvailabilityNotFoundException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return expertService.addSlots(addSlots);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/addAvailableDate")
    public ExpertAvailability addAvailableDate(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody AddAvailableDateToExpertAvailability addAvailableDate) throws InvalidArgumentException, ExpertAvailabilityNotFoundException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return expertService.addAvailableDate(addAvailableDate);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/updateSlot")
    public SlotDetails updateSlot(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody UpdateSlot updateSlot) throws SlotNotFoundException, InvalidArgumentException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return expertService.updateSlot(updateSlot);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/updateSlotStatus")
    public SlotDetails updateSlotStatus(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody UpdateSlotStatus updateSlotStatus, @RequestParam Optional<String> oldSlotId) throws SlotNotFoundException, InvalidArgumentException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return expertService.updateSlotStatus(updateSlotStatus, oldSlotId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/slot/{slotId}")
    public String deleteSlot(@RequestHeader HttpHeaders httpHeaders, @PathVariable String slotId) throws SlotNotFoundException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return ExpertConstants.MESSAGE_DELETE_SLOT + expertService.deleteSlot(slotId.trim());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/deleteAvailability")
    public String deleteService(@RequestHeader HttpHeaders httpHeaders, @RequestParam String expertEmail, @RequestParam Optional<String> serviceName) throws SlotNotFoundException, ExpertAvailabilityNotFoundException {
        ExpertUtils.setAccessTokenAndSecretKey(httpHeaders, secretKey);
        return ExpertConstants.MESSAGE_DELETE_SERVICE + expertService.deleteService(expertEmail, serviceName);
    }
}
