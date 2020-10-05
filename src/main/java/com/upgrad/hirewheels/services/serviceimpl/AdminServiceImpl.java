package com.upgrad.hirewheels.services.serviceimpl;

import com.upgrad.hirewheels.dao.VehicleDAO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleDAO.save(vehicle);
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Vehicle changeAvailability(int id, int availability_status) {
        vehicleDAO.changeAvailability(id, availability_status^1);
        return vehicleDAO.findById(id);
    }
}
