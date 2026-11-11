package com.flightbooking.booking_app.controller;

import com.flightbooking.booking_app.DataTransferObject.UserRegistrationRequest;
import com.flightbooking.booking_app.model.User;
import com.flightbooking.booking_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("api/users")

public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationRequest request) {
        User registeredUser = userService.registerUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(registeredUser);
    }
}