package com.example.paymentservice.web;


import com.example.paymentservice.entities.Payment;
import com.example.paymentservice.feign.UserRestClient;
import com.example.paymentservice.kafka.ProducerService.PaymentService;
import com.example.paymentservice.kafka.dto.PaymentRequest;
import com.example.paymentservice.models.User;
import com.example.paymentservice.repositories.PaymentRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author abdellah
 **/
@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {
    private PaymentRepository paymentRepository;
    private UserRestClient userRestClient;
    private PaymentService paymentService;


    public PaymentRestController(PaymentRepository paymentRepository, UserRestClient userRestClient, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.userRestClient = userRestClient;
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(payment);
    }
    @GetMapping(path = "/{id}")
    public Payment getPayment(@PathVariable Long id){
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (!paymentOptional.isPresent()) {
            throw new ResourceNotFoundException("Payment with id " + id + " not found");
        }
        Payment payment = paymentRepository.findById(id).get();
        User user = userRestClient.getUserById(payment.getUserId());
        payment.setUser(user);
        return payment;
    }
    @GetMapping
    public List<Payment> paymentsList(){
        paymentRepository.findAll().forEach(payment -> {
            payment.setUser(userRestClient.getUserById(payment.getUserId()));
        });
        return paymentRepository.findAll();
    }
}
