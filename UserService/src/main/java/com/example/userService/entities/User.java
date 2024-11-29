package com.example.userService.entities;

import com.example.userService.models.UserDonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author abdellah
 **/
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    private Long Id;
    private String name;
    private String email;
    @Transient // Prevent persistence of this field in the database
    private List<UserDonDTO> userDonDTOS;

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userDonDTOS=" + userDonDTOS +
                '}';
    }
}
