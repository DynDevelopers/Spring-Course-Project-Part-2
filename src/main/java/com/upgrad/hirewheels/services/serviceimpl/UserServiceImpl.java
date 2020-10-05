package com.upgrad.hirewheels.services.serviceimpl;

import com.upgrad.hirewheels.dao.UsersDAO;
import com.upgrad.hirewheels.dto.UserDTO;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.services.UserService;
import com.upgrad.hirewheels.utils.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    private List<String> tokenStore;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostConstruct
    public void init() {
        tokenStore = new ArrayList<>();
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (usersDAO.findUserByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistsException("Email Already Exists.");

        if (usersDAO.findUserByMobileNo(user.getMobileNo()) != null)
            throw new UserAlreadyExistsException("Mobile Number Already Exists.");

        addToken(jwtTokenProvider.createToken(user.getEmail()));
        return usersDAO.save(user);
    }

    @Override
    public User getUser(String email, String password) throws UnauthorizedUserException, UserNotRegisteredException {
        User user = usersDAO.findUserByEmail(email);
        if (user == null)
            throw new UserNotRegisteredException("User not Registered");

        if (!user.getPassword().equals(password))
            throw new UnauthorizedUserException("Unauthorized User");

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return usersDAO.findUserByEmail(email);
    }

    @Override
    public User getUserByMobileNo(String mobileNo) {
        return usersDAO.findUserByMobileNo(mobileNo);
    }

    @Override
    public User findById(int id) {
        return usersDAO.findById(id).get();
    }

    @Override
    public boolean isTokenPresent(String token) {
        return tokenStore.contains(token);
    }

    @Override
    public void addToken(String token) {
        tokenStore.add(token);
    }


}
