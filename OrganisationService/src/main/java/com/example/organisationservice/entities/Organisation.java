package com.example.organisationservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String contactEmail;
    private String phoneNumber;
    private String address;
    private boolean isVerified;

}
