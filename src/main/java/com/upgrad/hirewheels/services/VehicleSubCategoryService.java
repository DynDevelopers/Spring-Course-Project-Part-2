package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleSubCategoryDAO;
import com.upgrad.hirewheels.entities.VehicleSubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("vehicleSubCategoryService")
public class VehicleSubCategoryService {

    @Autowired
    private VehicleSubCategoryDAO vehicleSubCategoryDAO;

    public VehicleSubCategory getVehicleSubCategory(int id) {
        return vehicleSubCategoryDAO.findById(id);
    }
}
