package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.LocationDAO;
import com.upgrad.hirewheels.entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("locationService")
public class LocationService {

    @Autowired
    private LocationDAO locationDAO;

    public Location findById(int id) {
        return locationDAO.findById(id);
    }
}
