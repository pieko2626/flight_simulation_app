package com.flightbooking.booking_app.repository;

import com.flightbooking.booking_app.model.Status;
import com.flightbooking.booking_app.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository 
public interface BookingRepository extends JpaRepository<Booking, Long>{
    List<Booking> findByUserId(Long userId);
    List<Booking> findByFlightId(Long flightId);
    List<Booking> findByUserIdAndStatus(Long userId, Status status);
}
