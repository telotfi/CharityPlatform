package com.example.donservice.dtos;

import com.example.donservice.entities.Don;
import com.example.donservice.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author abdellah
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDonDTO {
    private Long id;
    private double amount;
    private LocalDate localDate;
    private User user;
}
