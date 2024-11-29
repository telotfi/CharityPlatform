package com.example.organisationservice.entities;

import com.example.organisationservice.models.DonDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Transient // To avoid persisting this field
    private List<DonDTO> dons;

    @Override
    public String toString() {
        return "Organisation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }
}
