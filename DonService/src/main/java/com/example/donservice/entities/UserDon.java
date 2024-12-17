package com.example.donservice.entities;

import com.example.donservice.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author abdellah
 **/
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "don_id", nullable = false)
    private Don don;
    private Long userId;
    private double amount;
    private LocalDate localDate;
    @Transient
    private User user;

    @Override
    public String toString() {
        return "UserDon{" +
                "id=" + id +
                ", don=" + don +
                ", userId=" + userId +
                ", amount=" + amount +
                ", localDate=" + localDate +
                '}';
    }
}
