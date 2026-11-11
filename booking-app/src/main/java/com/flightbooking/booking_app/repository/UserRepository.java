package com.flightbooking.booking_app.repository;

import com.flightbooking.booking_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
