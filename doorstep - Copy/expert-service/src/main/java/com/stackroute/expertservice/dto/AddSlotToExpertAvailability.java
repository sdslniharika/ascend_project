package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class AddSlotToExpertAvailability {

    @Schema(example = "doorstp.service@gmail.com")
    @Length(min = 4, max = 50, message = ExpertConstants.INVALID + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.LENGTH_BETWEEN)
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.CANT_BE_BLANK)
    private String expertEmail;
    @Schema(example = "Plumbing")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.CANT_BE_BLANK)
    private String serviceName;
    @Schema(example = "2023-01-10")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_DATE + ExpertConstants.CANT_BE_BLANK)
    private String slotDate;
    @NotEmpty(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOTS + ExpertConstants.CANT_BE_EMPTY)
    private Set<@Valid AddSlot> slots;

    public String getExpertEmail() {
        return expertEmail;
    }

    public void setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public Set<AddSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<AddSlot> slots) {
        this.slots = slots;
    }
}
