package com.upgrad.hirewheels.services.serviceimpl;

import com.upgrad.hirewheels.dao.VehicleDAO;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }

    @Override
    public Vehicle getVehicleByUserID(int id) {
        return null;
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleDAO.findAllByAvailableStatus();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        vehicleDAO.save(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle changeVehicleAvailability(int id) {
        Vehicle vehicle = vehicleDAO.findById(id);
        vehicleDAO.changeAvailability(id, vehicle.getAvailabilityStatus()^1);
        return vehicleDAO.findById(id);
    }

    @Override
    public Vehicle findById(int id) {
        return vehicleDAO.findById(id);
    }

    @Override
    public List<Vehicle> findVehicles(String categoryName, ZonedDateTime pickUpDate, ZonedDateTime dropDate, Location locationId) {
        return vehicleDAO.findVehicles(categoryName, pickUpDate, dropDate, locationId);
    }
}
