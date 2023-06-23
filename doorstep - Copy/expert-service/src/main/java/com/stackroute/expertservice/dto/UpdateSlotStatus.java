package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class UpdateSlotStatus {

    @Schema(example = "abcd1234abcd1234abcd1234")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_STATUS + ExpertConstants.CANT_BE_BLANK)
    private String slotId;
    @Schema(example = "AVAILABLE")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_STATUS + ExpertConstants.CANT_BE_BLANK)
    private String slotStatus;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }
}
