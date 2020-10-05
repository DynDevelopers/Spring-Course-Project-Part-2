package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.stereotype.Service;

@Service("adminService")
public interface AdminService {
    public Vehicle registerVehicle(Vehicle vehicle);
    public Vehicle changeAvailability(int id, int availability_status);
}
