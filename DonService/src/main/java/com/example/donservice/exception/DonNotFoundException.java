package com.example.donservice.exception;

/**
 * @author abdellah
 **/
public class DonNotFoundException extends RuntimeException {
    public DonNotFoundException(String message) {
        super(message);
    }
}
