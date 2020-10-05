package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dto.UserDTO;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import org.springframework.stereotype.Service;

@Service("userService")
public interface UserService {
    public User createUser(User user) throws UserAlreadyExistsException;
    public User getUser(String email, String password)  throws UnauthorizedUserException, UserNotRegisteredException;
    public User getUserByEmail(String email);
    public User getUserByMobileNo(String mobileNo);
    public User findById(int id);
    public boolean isTokenPresent(String token);
    public void addToken(String token);
}
