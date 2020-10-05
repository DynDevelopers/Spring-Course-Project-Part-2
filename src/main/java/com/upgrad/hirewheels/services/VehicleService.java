package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.Vehicle;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface VehicleService {
    public List<Vehicle> getAllVehicles ();
    public Vehicle getVehicleByUserID(int id);
    public List<Vehicle> getAvailableVehicles();
    public Vehicle saveVehicle(Vehicle vehicle);
    public Vehicle changeVehicleAvailability(int id);
    public Vehicle findById(int id);
    public List<Vehicle> findVehicles(String categoryName, ZonedDateTime pickUpDate, ZonedDateTime dropDate, Location locationId);
}

