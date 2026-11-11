package com.flightbooking.booking_app.service;

import com.flightbooking.booking_app.model.User;
import com.flightbooking.booking_app.exceptions.InsufficientFundsException;
import com.flightbooking.booking_app.exceptions.ResourceNotFoundException;
import com.flightbooking.booking_app.model.Booking;
import com.flightbooking.booking_app.model.Flight;
import com.flightbooking.booking_app.model.Status;
import com.flightbooking.booking_app.repository.BookingRepository;
import com.flightbooking.booking_app.repository.FlightRepository;
import com.flightbooking.booking_app.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
// import org.springframework.web.servlet.resource.ResourceTransformer;

import java.time.LocalDateTime;
import java.util.List;

@Service 
public class FlightService {
    
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public FlightService(FlightRepository flightRepository, BookingRepository bookingRepository, UserRepository userRepository){
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight addFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public List<Flight> findFlight(String origin, String destination){
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

    @Transactional 
    public Booking bookFlight(Long userId, Long flightId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User has not been found"));
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("Flight has not been found"));

        if (flight.getAvailableSeats() <= 0){
            throw new ResourceNotFoundException("No seats available for this flight");
        }

        if (user.getBalance().compareTo(flight.getFlightPrice()) < 0){
            throw new InsufficientFundsException("You don't have enough money to afford this flight");
        }

        user.setBalance(user.getBalance().subtract(flight.getFlightPrice()));
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);

        Booking booking = new Booking(user, flight, LocalDateTime.now(), Status.CONFIRMED);
        userRepository.save(user);
        flightRepository.save(flight);
        return bookingRepository.save(booking);


    }


}
