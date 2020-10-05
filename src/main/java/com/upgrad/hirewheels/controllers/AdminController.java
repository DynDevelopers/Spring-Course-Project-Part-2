package com.upgrad.hirewheels.controllers;


import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.FuelType;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.VehicleSubCategory;
import com.upgrad.hirewheels.exceptions.InvalidLocationIdException;
import com.upgrad.hirewheels.exceptions.NullValueException;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.services.*;
import com.upgrad.hirewheels.utils.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private FuelTypeService fuelTypeService;

    @Autowired
    private VehicleSubCategoryService vehicleSubCategoryService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/vehicles")
    public ResponseEntity<Object> addVehicle(@RequestBody VehicleDTO vehicleDTO, @RequestHeader(value = "X-Access-Token") String accessToken) throws InvalidLocationIdException, NullValueException, UnauthorizedUserException {

        Location location = locationService.findById(vehicleDTO.getLocationId());
        FuelType fuelType = fuelTypeService.findFuelTypeById(vehicleDTO.getFuelTypeId());
        VehicleSubCategory vehicleSubCategory = vehicleSubCategoryService.getVehicleSubCategory(vehicleDTO.getVehicleSubCategoryId());
        User user = userService.findById(vehicleDTO.getUserId());

        validate(vehicleDTO, location, fuelType, vehicleSubCategory, user);
        validateAccessToken(accessToken);

        vehicleService.saveVehicle(new POJOConverter().vehicleDTOToVehicleEntity(vehicleDTO, vehicleSubCategory, fuelType, location, user));

        Map<String, String> resposne = new HashMap<>();
        resposne.put("timestamp", String.valueOf(new Timestamp(10000).getTime()));
        resposne.put("message", "Vehicle Added Successfully");
        resposne.put("statusCode", HttpStatus.OK.toString());

        return new ResponseEntity<>(resposne, HttpStatus.OK);
    }

    private void validateAccessToken(String accessToken) throws UnauthorizedUserException {
        String emailId = jwtTokenProvider.getUsername(accessToken);
        if (!emailId.equals("upgrad@gmail.com"))
            throw new UnauthorizedUserException("Unauthorized. Only 'Admin' can access this API");
    }

    private void validate(VehicleDTO vehicleDTO, Location location, FuelType fuelType, VehicleSubCategory vehicleSubCategory, User user) throws NullValueException, InvalidLocationIdException {
        if (vehicleDTO.getVehicleModel().isEmpty() || vehicleDTO.getVehicleNumber().isEmpty() || vehicleDTO.getColor().isEmpty() || vehicleDTO.getVehicleImageUrl().isEmpty())
            throw new NullValueException("Fields shouldn’t be null or empty");

        if (location == null)
             throw new InvalidLocationIdException("Invalid Location Id for vehicle");

        if (fuelType == null || vehicleSubCategory == null)
            throw new NullValueException("Fields shouldn’t be null or empty");
    }

    @GetMapping("/vehicles/{vehicleid}")
    public ResponseEntity<Object> changeVehicleAvailability(@PathVariable("vehicleid") int vehicleid) {
        vehicleService.changeVehicleAvailability(vehicleid);
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", String.valueOf(new Timestamp(10000).getTime()));
        response.put("message", "Activity Performed Successfully");
        response.put("statusCode", HttpStatus.OK.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
