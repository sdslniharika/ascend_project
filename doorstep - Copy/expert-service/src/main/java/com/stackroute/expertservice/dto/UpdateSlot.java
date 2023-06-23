package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class UpdateSlot {


    @Schema(example = "abcd1234abcd1234abcd1234")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_STATUS + ExpertConstants.CANT_BE_BLANK)
    private String slotId;
    @Schema(example = "13:00:00")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_START_TIME + ExpertConstants.CANT_BE_BLANK)
    private String slotStartTime;
    @Schema(example = "13:30:00")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_END_TIME + ExpertConstants.CANT_BE_BLANK)
    private String slotEndTime;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(String slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public String getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(String slotEndTime) {
        this.slotEndTime = slotEndTime;
    }
}
