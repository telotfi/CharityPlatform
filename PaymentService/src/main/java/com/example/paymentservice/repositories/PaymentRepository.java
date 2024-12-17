package com.example.paymentservice.repositories;

import com.example.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author abdellah
 **/
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
