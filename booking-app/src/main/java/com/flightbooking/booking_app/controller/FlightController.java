package com.flightbooking.booking_app.controller;

import com.flightbooking.booking_app.model.Flight;
import com.flightbooking.booking_app.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping 
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @PostMapping 
    public ResponseEntity<Flight> addFlight(@Valid @RequestBody Flight flight){
        Flight savedFlight = flightService.addFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlight(@RequestParam String origin, @RequestParam String destination){
        return ResponseEntity.ok(flightService.findFlight(origin, destination));
    }

}
