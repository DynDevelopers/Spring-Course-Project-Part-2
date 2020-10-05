package com.upgrad.hirewheels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BookingDTO {

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("vehicleId")
    private int vehicleId;

    @JsonProperty("pickupDate")
    private String pickupDate;

    @JsonProperty("dropoffDate")
    private String dropoffDate;

    @JsonProperty("bookingDate")
    private String bookingDate;

    @JsonProperty("locationId")
    private int locationId;

    @JsonProperty("amount")
    private int amount;
}
