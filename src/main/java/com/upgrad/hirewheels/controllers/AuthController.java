package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dto.UserDTO;
import com.upgrad.hirewheels.dto.UserLoginDTO;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.NullValueException;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotFoundException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @RequestMapping(method = RequestMethod.POST, value = "/users/access-token")
    public ResponseEntity<Object> userSignIn(@RequestBody UserLoginDTO userLoginDTO) throws NullValueException, UserNotRegisteredException, UnauthorizedUserException {
        System.out.println(userLoginDTO.toString());
        validate(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        User user = userService.getUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        String token = jwtTokenProvider.createToken(user.getEmail());
        userService.addToken(token);

        Map<String, String> response = new HashMap<>();
        response.put("userId", String.valueOf(user.getUserId()));
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("email", user.getEmail());
        response.put("mobileNumber", user.getMobileNo());
        response.put("walletMoney", user.getWalletMoney().toString());
        response.put("roleName", user.getRole().getRoleName());
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validate(String emailId, String password) throws NullValueException {
        if (emailId.isEmpty() || password.isEmpty())
            throw new NullValueException("Email Id and Password are required fields");
    }
}
