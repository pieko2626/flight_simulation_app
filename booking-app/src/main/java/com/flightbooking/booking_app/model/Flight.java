package com.flightbooking.booking_app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity 
@Table(name = "flights")
public class Flight {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flightNumber;
    
    @Column(nullable = false, length = 3)
    @Size(min = 3, max = 3, message = "The airport code needs to be 3 letters long.")
    @Pattern(regexp = "^[A-Z]{3}", message = "The airport code needs to be 3 big letters long.")
    private String origin;

    @Column(nullable = false, length = 3)
    @Size(min = 3, max = 3, message = "The airport code needs to be 3 letters long.")
    @Pattern(regexp = "^[A-Z]{3}", message= "The airport code needs to be 3 big letters long.")
    private String destination;

    @Column(nullable = false)
    private BigDecimal flightPrice;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private int availableSeats;

    public Flight(){

    }

    public Flight(String flightNumber, String origin, String destination, BigDecimal flightPrice, LocalDateTime arrivalTime, LocalDateTime departureTime, int availableSeats){
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.flightPrice = flightPrice;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFlightNumber(){
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }

    public String getOrigin(){
        return origin;
    }

    public void setOrigin(String origin){
        this.origin = origin;
    }

    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public BigDecimal getFlightPrice(){
        return flightPrice;
    } 

    public void setFlightPrice(BigDecimal flightPrice){
        this.flightPrice = flightPrice;
    }

    public LocalDateTime getArrivalTime(){
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime(){
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime){
        this.departureTime = departureTime;
    }

    public int getAvailableSeats(){
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats){
        this.availableSeats = availableSeats;
    }
}
