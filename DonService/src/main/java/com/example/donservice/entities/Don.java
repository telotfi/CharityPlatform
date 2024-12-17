package com.example.donservice.entities;

import com.example.donservice.models.Organisation;
import com.example.donservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abdellah
 **/
@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "organisation_id", nullable = false)
    private Long organisationId;
    private String title;
    private String description;
    private double montantToAchieve;
    private double currentAmount = 0.0;
    private boolean isAchieved = false;
    @JsonIgnore
    @OneToMany(mappedBy = "don", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserDon> userDons = new ArrayList<>();
    @Transient
    private Organisation organisation;


    public Don(Long organisationId, String title, String description, double montantToAchieve, double currentAmount, boolean isAchieved) {
        this.organisationId = organisationId;
        this.title = title;
        this.description = description;
        this.montantToAchieve = montantToAchieve;
        this.currentAmount = currentAmount;
        this.isAchieved = isAchieved;
    }

    public void updateCurrentAmount(double amount) {
        this.currentAmount += amount;
        this.isAchieved = this.currentAmount >= this.montantToAchieve;
    }


    @Override
    public String toString() {
        return "Don{" +
                "id=" + id +
                ", organisationId=" + organisationId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", montantToAchieve=" + montantToAchieve +
                ", currentAmount=" + currentAmount +
                ", isAchieved=" + isAchieved +
                '}'; // Avoid userDons here
    }

}
