package com.flightbooking.booking_app;

import com.flightbooking.booking_app.exceptions.InsufficientFundsException;
import com.flightbooking.booking_app.model.Flight;
import com.flightbooking.booking_app.model.User;
import com.flightbooking.booking_app.repository.BookingRepository;
import com.flightbooking.booking_app.repository.FlightRepository;
import com.flightbooking.booking_app.repository.UserRepository;
import com.flightbooking.booking_app.service.FlightService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {
    
    @Mock 
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock 
    private BookingRepository bookingRepository;

    @InjectMocks 
    private FlightService flightService;

    @Test 
    void shouldThrowExceptionWhenUserHasInsufficientFunds(){
        User poorUser = new User();
        poorUser.setId(1L);
        poorUser.setBalance(new BigDecimal("50.00"));

        Flight expensiveFlight = new Flight();
        expensiveFlight.setId(1L);
        expensiveFlight.setFlightPrice(new BigDecimal("500.00"));
        expensiveFlight.setAvailableSeats(5);

        when(userRepository.findById(1L)).thenReturn(Optional.of(poorUser));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(expensiveFlight));

        assertThrows(InsufficientFundsException.class, () -> {
            flightService.bookFlight(1L, 1L);
        });
    }
        


}
