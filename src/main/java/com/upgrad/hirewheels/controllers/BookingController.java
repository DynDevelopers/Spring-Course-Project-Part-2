package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import com.upgrad.hirewheels.exceptions.InvalidDateException;
import com.upgrad.hirewheels.services.*;
import com.upgrad.hirewheels.utils.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class BookingController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<Object> addbooking(@RequestBody BookingDTO bookingDTO) throws InsufficientBalanceException, InvalidDateException {

        validateBookingInfo(bookingDTO);

        Location location = locationService.findById(bookingDTO.getLocationId());
        Vehicle vehicle = vehicleService.findById(bookingDTO.getVehicleId());
        User user = userService.findById(bookingDTO.getUserId());

        bookingService.addBooking(new POJOConverter().bookingDTOToBookingEntity(bookingDTO, location, vehicle, user));
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    private void validateBookingInfo(BookingDTO bookingDTO) throws InvalidDateException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate bookingDate = LocalDate.parse(bookingDTO.getBookingDate());
        LocalDate currentDate = LocalDate.now();

        if (bookingDate.compareTo(currentDate) != 0)
            throw new InvalidDateException("Booking date should be today's date");
    }
}
