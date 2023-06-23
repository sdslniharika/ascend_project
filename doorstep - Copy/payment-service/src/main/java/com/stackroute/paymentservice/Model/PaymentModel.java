package com.stackroute.paymentservice.Model;



import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="Payment")
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;
    private String bookingId;
    @Schema(example = "userservice030@gmail.com")
    private String userEmailId;
    @Schema(example = "499")
    private float totalPrice;
    @Schema(example = "CONFIRMED")
    private String status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String razorpayOrderId;

}

