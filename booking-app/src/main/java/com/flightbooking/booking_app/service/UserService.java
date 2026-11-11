package com.flightbooking.booking_app.service;

import com.flightbooking.booking_app.model.Role;
import com.flightbooking.booking_app.model.User;
import com.flightbooking.booking_app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Service 
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password){
        if (userRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("User with this email is already registered.");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(Role.USER);
        newUser.setBalance(new BigDecimal("0.00"));

        return userRepository.save(newUser);
    }

}
