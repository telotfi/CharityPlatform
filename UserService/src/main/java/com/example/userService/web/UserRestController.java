package com.example.userService.web;

import com.example.userService.entities.User;
import com.example.userService.repositories.UserRepository;
import com.example.userService.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author abdellah
 **/
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserRestController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers (){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/{userId}/donations")
    public ResponseEntity<User> getUserWithDonations(@PathVariable Long userId) {
        User user = userService.getUserWithDonations(userId);
        return ResponseEntity.ok(user);
    }



}
