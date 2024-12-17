package com.example.paymentservice.kafka.ProducerService;

import com.example.paymentservice.entities.Payment;
import com.example.paymentservice.enums.PaymentMethod;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.kafka.dto.PaymentRequest;

import com.example.paymentservice.repositories.PaymentRepository;
import com.example.shared.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author abdellah
 **/
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Value("${kafka.topic.payment-confirmation}")
    private String paymentConfirmationTopic;

    public Payment processPayment(PaymentRequest paymentRequest) {
        // Simulate payment processing
        Payment payment = new Payment();
        payment.setUserId(paymentRequest.getUserId());
        payment.setDonationId(paymentRequest.getDonationId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setMethod(PaymentMethod.CREDIT_CARD);
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaymentDate(LocalDateTime.now());

        // Save the payment to the database
        payment = paymentRepository.save(payment);

        // Produce an event to Kafka
        PaymentEvent paymentEvent = new PaymentEvent(payment.getDonationId(), payment.getUserId(), payment.getAmount());
        kafkaTemplate.send(paymentConfirmationTopic, paymentEvent);

        return payment;
    }
}
