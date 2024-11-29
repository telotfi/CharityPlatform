package com.example.userService.models;

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
    private Long donId;
    private double amount;
    private LocalDate localDate;
}
