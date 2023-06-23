package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class AddAvailableDateToExpertAvailability {

    @Schema(example = "doorstp.service@gmail.com")
    @Length(min = 4, max = 50, message = ExpertConstants.INVALID + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.LENGTH_BETWEEN)
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.CANT_BE_BLANK)
    private String expertEmail;
    @Schema(example = "Plumbing")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.CANT_BE_BLANK)
    private String serviceName;
    @Valid
    private AddAvailableDate availableDate;

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

    public AddAvailableDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(AddAvailableDate availableDate) {
        this.availableDate = availableDate;
    }
}
