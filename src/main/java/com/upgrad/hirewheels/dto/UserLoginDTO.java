package com.upgrad.hirewheels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLoginDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public UserLoginDTO() {}

    public UserLoginDTO(String email, String password) {
        this.password = password;
        this.email = email;
    }
}
