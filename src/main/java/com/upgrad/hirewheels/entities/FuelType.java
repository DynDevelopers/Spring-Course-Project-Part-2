package com.upgrad.hirewheels.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class FuelType {
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "fuel_type_id")
    private int fuelTypeId;

    @Column(name = "fuel_type", nullable = false, unique = true)
    private String fuelType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fuelType")
    private List<Vehicle> vehicles;
}
