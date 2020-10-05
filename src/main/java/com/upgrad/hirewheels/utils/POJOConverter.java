package com.upgrad.hirewheels.utils;


import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.dto.UserDTO;
import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.*;
import com.upgrad.hirewheels.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class POJOConverter {
    public static User userDTOToUserEntity(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setMobileNo(userDTO.getMobileNo());
        user.setWalletMoney(10000);

        Role role = new Role();
        role.setRoleId(2);
        role.setRoleName("USER");

        user.setRole(role);
        return user;
    }

    public Vehicle vehicleDTOToVehicleEntity(VehicleDTO vehicleDTO, VehicleSubCategory vehicleSubCategory, FuelType fuelType, Location location, User user) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setVehicleSubCategory(vehicleSubCategory);
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setFuelType(fuelType);
        vehicle.setLocation(location);
        vehicle.setVehicleImageUrl(vehicleDTO.getVehicleImageUrl());
        vehicle.setAvailabilityStatus(vehicleDTO.getAvailabilityStatus());
        vehicle.setUser(user);
        return vehicle;
    }

    public Booking bookingDTOToBookingEntity(BookingDTO bookingDTO, Location location, Vehicle vehicle, User user) {
        Booking booking = new Booking();

        booking.setPickupDate(ZonedDateTime.parse(bookingDTO.getPickupDate()));
        booking.setDropoffDate(ZonedDateTime.parse(bookingDTO.getDropoffDate()));
        booking.setBookingDate(LocalDate.parse(bookingDTO.getBookingDate()));
        booking.setAmount(bookingDTO.getAmount());
        booking.setLocation(location);
        booking.setVehicle(vehicle);
        booking.setUser(user);


        return booking;
    }
}
