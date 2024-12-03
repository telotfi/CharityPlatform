package com.example.donservice.dtos;

import com.example.donservice.entities.UserDon;
import com.example.donservice.models.Organisation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abdellah
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonDTO {
    private Long id;
    private Long organisationId;
    private String title;
    private String description;
    private double montantToAchieve;
    private double currentAmount;
    private boolean isAchieved;
    private Organisation organisation;
    private List<UserDonDTO> userDonDTOS = new ArrayList<>();
}