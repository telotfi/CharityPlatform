package com.example.paymentservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author abdellah
 **/
@Data
public class Don {
    private Long id;
    private Long userId;
    private Long organisationId;  // Reference to Organisation microservice
    private Double montant;
    private LocalDate date;


}

