package com.example.paymentservice;

import com.example.paymentservice.config.GlobalConfig;
import com.example.paymentservice.entities.Payment;
import com.example.paymentservice.enums.PaymentMethod;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(GlobalConfig.class)
public class PaymentServiceApplication implements CommandLineRunner {
    @Autowired
    private PaymentRepository paymentRepository;
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Payment payment1 = new Payment(null, 1L, 1L, PaymentMethod.CREDIT_CARD, PaymentStatus.COMPLETED, LocalDateTime.now());
//        Payment payment2 = new Payment(null, 2L, 2L, PaymentMethod.PAYPAL, PaymentStatus.PENDING, LocalDateTime.now().minusDays(1));
//        Payment payment3 = new Payment(null, 3L, 3L, PaymentMethod.BANK_TRANSFER, PaymentStatus.FAILED, LocalDateTime.now().minusHours(3));
//
//        // Save sample payments to the database
//        paymentRepository.save(payment1);
//        paymentRepository.save(payment2);
//        paymentRepository.save(payment3);
        paymentRepository.findAll().forEach(c -> {
            System.out.println(c.toString());
        });
    }
}
