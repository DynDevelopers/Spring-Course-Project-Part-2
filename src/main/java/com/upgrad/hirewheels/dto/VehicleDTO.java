package com.upgrad.hirewheels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.FuelType;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.VehicleSubCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class VehicleDTO {

    @JsonProperty("vehicleModel")
    private String vehicleModel;

    @JsonProperty("vehicleNumber")
    private String vehicleNumber;

    @JsonProperty("color")
    private String color;

    @JsonProperty("locationId")
    private int locationId;

    @JsonProperty("vehicleSubCategoryId")
    private int vehicleSubCategoryId;

    @JsonProperty("fuelTypeId")
    private int fuelTypeId;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("availabilityStatus")
    private int availabilityStatus;

    @JsonProperty("vehicleImageUrl")
    private String vehicleImageUrl;
}
