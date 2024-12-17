package com.example.donservice.kafka.ConsumerService;

import com.example.donservice.services.DonService;
import com.example.shared.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author abdellah
 **/
@Service
@RequiredArgsConstructor
public class PaymentEventConsumer {
    private final DonService donService;

    @KafkaListener(topics = "${kafka.topic.payment-confirmation}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentEvent(PaymentEvent paymentEvent) {
            donService.handleDonation(paymentEvent.getDonationId(), paymentEvent.getUserId(), paymentEvent.getAmount());
    }
}
