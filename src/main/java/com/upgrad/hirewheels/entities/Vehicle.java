package com.upgrad.hirewheels.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;
    @Column(name = "vehicleModel", nullable = false)
    private String vehicleModel;
    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @Column(name = "color", nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    @ManyToOne
    private VehicleSubCategory vehicleSubCategory;

    //    @JoinColumn(name="fuel_type_id")
    @ManyToOne
    private FuelType fuelType;

    @Column(name = "availability_status", nullable = false)
    private int availabilityStatus;

    @Column(name = "vehicle_image_url", nullable = false)
    private String vehicleImageUrl;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicle")
    private List<Booking> bookings;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", color='" + color + '\'' +
                ", location=" + location.getLocationName() +
                ", vehicleSubCategory=" + vehicleSubCategory.getVehicleSubcategoryName() +
                ", fuelType=" + fuelType.getFuelType() +
                ", availabilityStatus=" + availabilityStatus +
                ", vehicleImageUrl='" + vehicleImageUrl + '\'' +
                '}';
    }
}
