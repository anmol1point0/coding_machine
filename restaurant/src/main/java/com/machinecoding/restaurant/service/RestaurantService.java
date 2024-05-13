package com.machinecoding.restaurant.service;

import com.google.common.base.Preconditions;
import com.machinecoding.restaurant.dao.RestaurantDao;
import com.machinecoding.restaurant.dao.UserDao;
import com.machinecoding.restaurant.model.Restaurant;
import com.machinecoding.restaurant.model.User;
import com.machinecoding.restaurant.request.RestaurantFeedbackRequest;
import com.machinecoding.restaurant.request.RestaurantRegistrationRequest;
import com.machinecoding.restaurant.request.UpdateRestaurantDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    @Autowired
    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public Restaurant registerRestaurant(RestaurantRegistrationRequest restaurantRegistrationRequest){
        Preconditions.checkArgument(Objects.nonNull(restaurantRegistrationRequest),"restaurantRegistrationRequest cannot be null while registering restaurant");

        User loggedInUser = UserDao.loggedInUser;

        return restaurantDao.registerRestaurants(restaurantRegistrationRequest, loggedInUser);
    }

    public Restaurant updateRestaurantDetails(UpdateRestaurantDetailsRequest updateRestaurantDetailsRequest){
        Preconditions.checkArgument(Objects.nonNull(updateRestaurantDetailsRequest),"");

        return restaurantDao.updateRestaurantDetails(updateRestaurantDetailsRequest);
    }

    public Boolean registerFeedback(RestaurantFeedbackRequest restaurantFeedback){
        Preconditions.checkArgument(Objects.nonNull(restaurantFeedback),"");

        return restaurantDao.registerFeedback(restaurantFeedback);
    }
}
