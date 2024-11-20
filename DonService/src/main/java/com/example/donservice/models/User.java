package com.example.donservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abdellah
 **/
@Data
public class User {
    private Long Id;
    private String name;
    private String email;

}
