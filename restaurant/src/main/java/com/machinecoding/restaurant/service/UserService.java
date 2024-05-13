package com.machinecoding.restaurant.service;

import com.google.common.base.Preconditions;
import com.machinecoding.restaurant.dao.UserDao;
import com.machinecoding.restaurant.model.User;
import com.machinecoding.restaurant.request.UserLoginRequest;
import com.machinecoding.restaurant.request.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Objects;

@Service
public class UserService {

    private final HashMap<String, User> registeredUser;
    private final HashMap<String, User> registeredPhoneNumber;
    private final UserDao userDao;

    @Autowired
    public UserService(HashMap<String, User> registeredUser,
                       HashMap<String, User> registeredPhoneNumber,
                       UserDao userDao) {
        this.registeredUser = registeredUser;
        this.registeredPhoneNumber = registeredPhoneNumber;
        this.userDao = userDao;
    }

    public User registerUser(UserRegistrationRequest userRegistrationRequest){
        Preconditions.checkArgument(Objects.nonNull(userRegistrationRequest.getPhoneNumber()),"Phone Number is needed while registering an user");

        String phoneNumber = userRegistrationRequest.getPhoneNumber();

        if(registeredPhoneNumber.containsKey(phoneNumber))
            throw new IllegalArgumentException("User Already Exists with phone Number: " + phoneNumber);

        return userDao.registerUser(userRegistrationRequest);
    }

    public User getUser(String phoneNumber){
        Preconditions.checkArgument(!StringUtils.isEmpty(phoneNumber),"Phone Number is needed while getting an user");

        return registeredUser.getOrDefault(phoneNumber, null);
    }

    public Boolean login(UserLoginRequest userLoginRequest){
        if(!registeredUser.containsKey(userLoginRequest.getUserId())){
            throw new IllegalArgumentException("NO user Exists with userId: " + userLoginRequest.getUserId());
        }
        return userDao.login(userLoginRequest.getUserId());
    }
}
