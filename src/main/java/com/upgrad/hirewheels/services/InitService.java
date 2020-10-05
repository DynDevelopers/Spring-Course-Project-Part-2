package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "initService")
public class InitService {
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private VehicleCategoryDAO vehicleCategoryDAO;

    @Autowired
    private VehicleSubCategoryDAO vehicleSubCategoryDAO;

    @Autowired
    private FuelTypeDAO fuelTypeDAO;

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private LocationDAO locationDAO;

    VehicleCategory vehicleCategory1 = new VehicleCategory();
    VehicleCategory vehicleCategory2 = new VehicleCategory();

    public void populateRole() {

        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("ADMIN");

        Role role2 = new Role();
        role2.setRoleId(2);
        role2.setRoleName("USER");

        roleDAO.save(role1);
        roleDAO.save(role2);
    }

    public void populateUserAdmin() {
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("upgrad@gmail.com");
        admin.setPassword("admin@123");
        admin.setMobileNo("9999999999");
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("ADMIN");
        admin.setRole(role);
        admin.setWalletMoney(1000);

        usersDAO.save(admin);
    }

    public void populateVehicleCategory() {

        vehicleCategory1.setVehicleCategoryId(10);
        vehicleCategory1.setVehicleCategoryName("Car");

        vehicleCategoryDAO.save(vehicleCategory1);


        vehicleCategory2.setVehicleCategoryId(11);
        vehicleCategory2.setVehicleCategoryName("Bike");

        vehicleCategoryDAO.save(vehicleCategory2);

    }

    public void populateVehicleSubCategory() {
        VehicleSubCategory vehicleSubCategory = new VehicleSubCategory();

        vehicleSubCategory.setVehicleSubcategoryId(1);
        vehicleSubCategory.setVehicleSubcategoryName("SUV");
        vehicleSubCategory.setPricePerDay(300);
        vehicleSubCategory.setVehicleCategory(vehicleCategory1);

        System.out.println(vehicleSubCategoryDAO.save(vehicleSubCategory).toString());

        vehicleSubCategory.setVehicleSubcategoryId(2);
        vehicleSubCategory.setVehicleSubcategoryName("SEDAN");
        vehicleSubCategory.setPricePerDay(350);
        vehicleSubCategory.setVehicleCategory(vehicleCategory1);

        System.out.println(vehicleSubCategoryDAO.save(vehicleSubCategory).toString());

        vehicleSubCategory.setVehicleSubcategoryId(3);
        vehicleSubCategory.setVehicleSubcategoryName("HATCHBACK");
        vehicleSubCategory.setPricePerDay(250);
        vehicleSubCategory.setVehicleCategory(vehicleCategory1);

        System.out.println(vehicleSubCategoryDAO.save(vehicleSubCategory).toString());

        vehicleSubCategory.setVehicleSubcategoryId(4);
        vehicleSubCategory.setVehicleSubcategoryName("CRUISER");
        vehicleSubCategory.setPricePerDay(200);
        vehicleSubCategory.setVehicleCategory(vehicleCategory1);

        System.out.println(vehicleSubCategoryDAO.save(vehicleSubCategory).toString());

        vehicleSubCategory.setVehicleSubcategoryId(5);
        vehicleSubCategory.setVehicleSubcategoryName("DIRT BIKE");
        vehicleSubCategory.setPricePerDay(200);
        vehicleSubCategory.setVehicleCategory(vehicleCategory2);

        System.out.println(vehicleSubCategoryDAO.save(vehicleSubCategory).toString());

        vehicleSubCategory.setVehicleSubcategoryId(6);
        vehicleSubCategory.setVehicleSubcategoryName("SPORTS BIKE");
        vehicleSubCategory.setPricePerDay(150);
        vehicleSubCategory.setVehicleCategory(vehicleCategory2);

        System.out.println(vehicleSubCategoryDAO.save(vehicleSubCategory).toString());
    }

    public void populateFuelType() {

        FuelType fuelType = new FuelType();

        fuelType.setFuelTypeId(1);
        fuelType.setFuelType("Petrol");

        System.out.println(fuelTypeDAO.save(fuelType).toString());

        fuelType.setFuelTypeId(2);
        fuelType.setFuelType("Diesel");

        System.out.println(fuelTypeDAO.save(fuelType).toString());

    }

    public void populateCity() {

        City city = new City();
        city.setCityId(1);
        city.setCityName("Mumbai");

        cityDAO.save(city);
    }

    public void populateLocation() {

        Location location = new Location();

        /* Location Worli */
        location.setLocationId(1);
        location.setLocationName("Worli");
        location.setAddress("Dr E Moses Rd, Worli Naka, Upper Worli");

        City city = new City();
        city.setCityId(1);
        city.setCityName("Mumbai");

        location.setCity(city);

        location.setPincode("400018");

        locationDAO.save(location);

        /* Location Chembur */
        location.setLocationId(2);
        location.setLocationName("Chembur");
        location.setAddress("Optic Complex");

        location.setCity(city);

        location.setPincode("400019");

        locationDAO.save(location);

        /* Location Powai */
        location.setLocationId(3);
        location.setLocationName("Powai");
        location.setAddress("Hiranandani Towers");

        location.setCity(city);

        location.setPincode("400020");

        locationDAO.save(location);
    }
}