package com.upgrad.hirewheels.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;


@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "pickup_date")
    private ZonedDateTime pickupDate;

    @Column(name = "dropoff_date")
    private ZonedDateTime dropoffDate;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

//    @Column(name = "user_id")
//    private int userId;
}