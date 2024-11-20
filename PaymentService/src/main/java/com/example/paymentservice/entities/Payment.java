package com.example.paymentservice.entities;

import com.example.paymentservice.enums.PaymentMethod;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.models.Don;
import com.example.paymentservice.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.IdentityGenerator;

import java.time.LocalDateTime;

/**
 * @author abdellah
 **/
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long donationId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    @Transient
    private User user;
    @Transient
    private Don don;

//    public Payment(Object o, long l, long l1, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime now) {
//    }

}
