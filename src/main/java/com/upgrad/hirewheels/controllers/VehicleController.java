package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dto.FindVehicleDTO;
import com.upgrad.hirewheels.exceptions.InvalidDateException;
import com.upgrad.hirewheels.services.LocationService;
import com.upgrad.hirewheels.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private LocationService locationService;

    @PostMapping("/find")
    public ResponseEntity<Object> getVehicles (@RequestBody FindVehicleDTO findVehicleDTO) throws InvalidDateException {
        validate(findVehicleDTO);
        return new ResponseEntity<>(new ArrayList<>(vehicleService.findVehicles(findVehicleDTO.getCategoryName(), findVehicleDTO.getPickupDate(), findVehicleDTO.getDropoffDate(), locationService.findById(findVehicleDTO.getLocationId()))), HttpStatus.OK);
    }

    private void validate(FindVehicleDTO findVehicleDTO) throws InvalidDateException {
        LocalDate pickupDate = findVehicleDTO.getPickupDate().toLocalDate();
        LocalDate dropoffDate = findVehicleDTO.getDropoffDate().toLocalDate();
        LocalDate currentDate = ZonedDateTime.now().toLocalDate();

        System.out.println("Pickup Date: " + pickupDate);
        System.out.println("Dropoff Date: " + dropoffDate);
        System.out.println("Current Date: " + currentDate);

        if (pickupDate.compareTo(dropoffDate) > 0)
            throw new InvalidDateException("Drop-off date should be greater than today's date and greater than the pickup date");

        if (pickupDate.compareTo(currentDate) < 0)
            throw new InvalidDateException("Pickup date should not be less than today's date.");

        if (pickupDate.compareTo(currentDate) > 0)
            throw new InvalidDateException("Pickup date should not be greater than today's date.");
    }
}
