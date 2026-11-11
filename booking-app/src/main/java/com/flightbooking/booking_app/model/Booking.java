package com.flightbooking.booking_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity 
@Table(name = "bookings")
public class Booking {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @ManyToOne 
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public Booking(){
    }

    public Booking(User user, Flight flight, LocalDateTime bookingDate, Status status){
        this.user = user;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Flight flight(){
        return flight;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public LocalDateTime bookingDate(){
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate){
        this.bookingDate = bookingDate;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

}
