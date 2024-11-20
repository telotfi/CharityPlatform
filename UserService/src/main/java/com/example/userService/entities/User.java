package com.example.userService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author abdellah
 **/
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    private Long Id;
    private String name;
    private String email;


}
