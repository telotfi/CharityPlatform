package com.example.userService.web;

import com.example.userService.entities.User;
import com.example.userService.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abdellah
 **/
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/donations")
    public ResponseEntity<User> getUserWithDonations(@PathVariable Long userId) {
        User user = userService.getUserWithDonations(userId);
        return ResponseEntity.ok(user);
    }
}
