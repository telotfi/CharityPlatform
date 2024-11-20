package com.example.paymentservice.web;


import com.example.paymentservice.entities.Payment;
import com.example.paymentservice.feign.DonRestClient;
import com.example.paymentservice.feign.UserRestClient;
import com.example.paymentservice.models.Don;
import com.example.paymentservice.models.User;
import com.example.paymentservice.repositories.PaymentRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author abdellah
 **/
@RestController
public class PaymentRestController {
    private PaymentRepository paymentRepository;
    private UserRestClient userRestClient;
    private DonRestClient donRestClient;

    public PaymentRestController(PaymentRepository paymentRepository,UserRestClient userRestClient, DonRestClient donRestClient) {
        this.paymentRepository = paymentRepository;
        this.userRestClient = userRestClient;
        this.donRestClient = donRestClient;
    }
    @GetMapping(path = "/payments/{id}")
    public Payment getPayment(@PathVariable Long id){
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (!paymentOptional.isPresent()) {
            throw new ResourceNotFoundException("Payment with id " + id + " not found");
        }
        Payment payment = paymentRepository.findById(id).get();
        User user = userRestClient.getUserById(payment.getUserId());
        payment.setUser(user);
        Don don = donRestClient.getDonById(payment.getDonationId());
        payment.setDon(don);
        return payment;
    }
    @GetMapping(path = "/payments")
    public List<Payment> paymentsList(){
        paymentRepository.findAll().forEach(payment -> {
            payment.setUser(userRestClient.getUserById(payment.getUserId()));
            payment.setDon(donRestClient.getDonById(payment.getDonationId()));
        });
        return paymentRepository.findAll();
    }
}
