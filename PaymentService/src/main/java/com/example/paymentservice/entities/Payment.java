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
@Entity
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

    public Payment(Object o, long l, long l1, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime now) {
    }

    public Payment() {
    }

    public Payment(Long id, Long userId, Long donationId, PaymentMethod method, PaymentStatus status, LocalDateTime paymentDate) {
        this.id = id;
        this.userId = userId;
        this.donationId = donationId;
        this.method = method;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", donationId=" + donationId +
                ", method=" + method +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                ", user=" + user +
                ", don=" + don +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Don getDon() {
        return don;
    }

    public void setDon(Don don) {
        this.don = don;
    }
}
