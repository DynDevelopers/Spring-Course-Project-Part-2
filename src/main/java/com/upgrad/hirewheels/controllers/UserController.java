package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dto.UserDTO;
import com.upgrad.hirewheels.dto.UserLoginDTO;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.services.UserService;
import com.upgrad.hirewheels.utils.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userSignUp")
    public ResponseEntity<Object> userSignUp(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException, FirstNameEmptyException, PasswordLengthException, InvalidMobileNoException {

        if (userDTO.getFirstName().isEmpty())
            throw new FirstNameEmptyException("FirstName cannot be null or empty");

        if (userDTO.getPassword().length() < 5)
            throw new PasswordLengthException("Password cannot be null or less than 5 characters");

        if (userDTO.getMobileNo().length() < 10)
            throw new InvalidMobileNoException("Mobile Number cannot be null or empty and must be 10 digits");


        userService.createUser(POJOConverter.userDTOToUserEntity(userDTO));
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", String.valueOf(new Timestamp(10000).getTime()));
        response.put("statusCode", String.valueOf(HttpStatus.OK));
        response.put("message", "User Successfully Signed Up");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/users/access_token")
//    public ResponseEntity<Object> userLogin(@RequestBody UserLoginDTO userLoginDTO) throws UserNotRegisteredException, UnauthorizedUserException {
//        System.out.println(userLoginDTO.toString());
//        Map<String, String> response = new HashMap<>();
//        com.upgrad.hirewheels.entities.User user = null;
//        user = userService.getUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
//        response.put("userId", String.valueOf(user.getUserId()));
//        response.put("firstName", user.getFirstName());
//        response.put("lastName", user.getLastName());
//        response.put("email", user.getEmail());
//        response.put("mobileNumber", user.getMobileNo());
//        response.put("walletMoney", user.getWalletMoney().toString());
//        response.put("roleName", user.getRole().getRoleName());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
