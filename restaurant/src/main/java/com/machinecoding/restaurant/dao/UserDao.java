package com.machinecoding.restaurant.dao;

import com.machinecoding.restaurant.model.User;
import com.machinecoding.restaurant.request.UserRegistrationRequest;
import com.machinecoding.restaurant.statics.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDao {

    private final HashMap<Long, User> registeredUser;
    private final HashMap<String, User> registeredPhoneNumber;

    public static User loggedInUser = null;

    @Autowired
    public UserDao(HashMap<Long, User> registeredUser, HashMap<String, User> registeredPhoneNumber) {
        this.registeredUser = registeredUser;
        this.registeredPhoneNumber = registeredPhoneNumber;
    }

    public User registerUser(UserRegistrationRequest userRegistrationRequest){
        String phoneNumber = userRegistrationRequest.getPhoneNumber();

        if(registeredPhoneNumber.containsKey(phoneNumber))
            throw new IllegalArgumentException("User Already Exists with phone Number: " + phoneNumber);
        User user = new User();
        user.setUserId(IdGenerator.getId());
        user.setGender(userRegistrationRequest.getGender());
        user.setName(userRegistrationRequest.getName());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setPincode(userRegistrationRequest.getPincode());

        registeredUser.put(user.getUserId(), user);
        registeredPhoneNumber.put(phoneNumber, user);

        return user;
    }

    public Boolean login(Long userId){
        loggedInUser = registeredUser.get(userId);
        System.out.println("Successfully logged in user: " + userId);
        return true;
    }
}
