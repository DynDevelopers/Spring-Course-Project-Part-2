package com.upgrad.hirewheels.responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomResponse {

    private String message;
    private int statusCode;
    private Timestamp timestamp;
    public CustomResponse() {}

    public CustomResponse(Timestamp timestamp, String message, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;

    }
}
