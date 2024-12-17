package com.example.paymentservice.kafka.dto;

import com.example.paymentservice.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abdellah
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long userId;
    private Long donationId;
    private double amount;
}
