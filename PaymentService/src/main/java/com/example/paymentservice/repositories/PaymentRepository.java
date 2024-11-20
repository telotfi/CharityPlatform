package com.example.paymentservice.repositories;

import com.example.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author abdellah
 **/
@RepositoryRestResource
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
