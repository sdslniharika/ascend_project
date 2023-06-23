package com.stackroute.customerservice.service;

import com.stackroute.customerservice.dto.Slot;
import com.stackroute.customerservice.dto.UpdateSlotStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class SlotService {

    @Value("${expertService.baseUrl}")
    String baseUrl;

    private RestTemplate restTemplate;


    public SlotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Slot updateSlotStatus(UpdateSlotStatus updateSlotStatus, Optional<String> slotId) {
        String url= baseUrl + "/api/v1/availability/updateSlotStatus";
        HttpEntity<UpdateSlotStatus> slotHttpEntity = new HttpEntity<>(updateSlotStatus);
        ResponseEntity<Slot> responseEntity;
        if (slotId.isPresent()) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("oldSlotId", slotId.get());
            responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, slotHttpEntity, Slot.class);
        }else{
            responseEntity = restTemplate.exchange(url, HttpMethod.PUT, slotHttpEntity, Slot.class);
        }
        Slot slot = responseEntity.getBody();
        return slot;
    }


}
