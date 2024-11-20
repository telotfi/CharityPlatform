package com.example.donservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author abdellah
 **/
@Data
public class Organisation {
    private Long id;
    private String name;
    private String description;
    private String contactEmail;
    private String phoneNumber;
    private String address;
    private boolean isVerified;



}

