package com.upgrad.hirewheels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class FindVehicleDTO {

    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("pickupDate")
    private ZonedDateTime pickupDate;

    @JsonProperty("dropoffDate")
    private ZonedDateTime dropoffDate;

    @JsonProperty("locationId")
    private int locationId;
}
