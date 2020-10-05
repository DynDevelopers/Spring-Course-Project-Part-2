package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Location {
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "location_id")
    private int locationId;

    @Column(name = "location_name", nullable = false)
    private String locationName;
    @Column(nullable = false)
    private String address;

//    @Column(name = "city_id", nullable = false)
//    private int cityId;

    @Column(nullable = false)
    private String pincode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "location")
    private List<Vehicle> vehicles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "location")
    private List<Booking> bookings;

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;
}
