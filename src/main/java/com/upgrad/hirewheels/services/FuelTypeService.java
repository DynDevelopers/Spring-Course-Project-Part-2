package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.FuelTypeDAO;
import com.upgrad.hirewheels.entities.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fuelTypeService")
public class FuelTypeService {

    @Autowired
    private FuelTypeDAO fuelTypeDAO;

    public FuelType findFuelTypeById(int id) {
        return fuelTypeDAO.findById(id);
    }
}
