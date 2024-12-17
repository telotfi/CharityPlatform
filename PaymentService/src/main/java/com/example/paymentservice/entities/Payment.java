package com.example.paymentservice.entities;

import com.example.paymentservice.enums.PaymentMethod;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    @Transient
    private User user;


//    public Payment(Object o, long l, long l1, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime now) {
//    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", donationId=" + donationId +
                ", amount=" + amount +
                ", method=" + method +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
