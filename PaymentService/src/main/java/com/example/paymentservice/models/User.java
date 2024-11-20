package com.example.paymentservice.models;

import lombok.Data;
import lombok.ToString;

/**
 * @author abdellah
 **/
@Data
@ToString
public class User {
    private Long Id;
    private String name;
    private String email;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}