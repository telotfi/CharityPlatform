package com.example.donservice.entities;

import com.example.donservice.models.Organisation;
import com.example.donservice.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Long userId;
    private Long organisationId;  // Reference to Organisation microservice
    private Double montant;
    private LocalDate date;
    @Transient
    private User user;
    @Transient
    private Organisation organisation;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

//    public Don(Long id, Long userId, Long organisationId, Double montant, LocalDate date) {
//        this.id = id;
//        this.userId = userId;
//        this.organisationId = organisationId;
//        this.montant = montant;
//        this.date = date;
//    }
//
//     Getter and Setter methods for userId and organisationId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Don{" +
                "id=" + id +
                ", userId=" + userId +
                ", organisationId=" + organisationId +
                ", montant=" + montant +
                ", date=" + date +
                ", user=" + user +
                ", organisation=" + organisation +
                '}';
    }
}
