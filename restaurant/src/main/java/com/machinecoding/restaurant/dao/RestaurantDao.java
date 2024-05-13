package com.machinecoding.restaurant.dao;

import com.google.common.base.Preconditions;
import com.machinecoding.restaurant.model.Restaurant;
import com.machinecoding.restaurant.model.User;
import com.machinecoding.restaurant.request.RestaurantFeedbackRequest;
import com.machinecoding.restaurant.request.RestaurantRegistrationRequest;
import com.machinecoding.restaurant.request.UpdateRestaurantDetailsRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@Repository
public class RestaurantDao {

    private final HashMap<String, Restaurant> registeredRestaurants;

    public RestaurantDao(HashMap<String, Restaurant> registeredRestaurants) {
        this.registeredRestaurants = registeredRestaurants;
    }

    public Restaurant registerRestaurants(RestaurantRegistrationRequest restaurantRegistrationRequest, User loggedInUser){
        Preconditions.checkArgument(Objects.nonNull(restaurantRegistrationRequest),"");
        Preconditions.checkArgument(Objects.nonNull(loggedInUser),"");

        if(registeredRestaurants.containsKey(restaurantRegistrationRequest.getRestaurantName())){
            System.out.println("Already registered Restaurant");
            return registeredRestaurants.get(restaurantRegistrationRequest.getRestaurantName());
        }

        Restaurant restaurant = Restaurant.builder()
                .restaurantName(restaurantRegistrationRequest.getRestaurantName())
                .foodItemName(restaurantRegistrationRequest.getFoodItemName())
                .restaurantName(restaurantRegistrationRequest.getRestaurantName())
                .foodItemPrice(restaurantRegistrationRequest.getFoodItemPrice())
                .quantity(restaurantRegistrationRequest.getQuantity())
                .owner(loggedInUser)
                .build();

        registeredRestaurants.put(restaurantRegistrationRequest.getRestaurantName(), restaurant);

        System.out.println("Successfully registered restaurant : " + restaurantRegistrationRequest.getRestaurantName());

        return restaurant;
    }

    public Restaurant updateRestaurantDetails(UpdateRestaurantDetailsRequest updateRestaurantDetailsRequest){
        Restaurant restaurant = registeredRestaurants.get(updateRestaurantDetailsRequest.getRestaurantName());

        Preconditions.checkArgument(Objects.nonNull(restaurant),"No restaurant Present with name: " + updateRestaurantDetailsRequest.getRestaurantName());

        Integer currentQuantity = restaurant.getQuantity();

        System.out.println("Total: " + currentQuantity + " in restaurant: " + updateRestaurantDetailsRequest.getRestaurantName());

        currentQuantity += updateRestaurantDetailsRequest.getQuantityToAdd();

        restaurant.setQuantity(currentQuantity);

        registeredRestaurants.put(updateRestaurantDetailsRequest.getRestaurantName(), restaurant);

        System.out.println("Total: " + currentQuantity + " in restaurant: ");

        return restaurant;
    }

    public Boolean registerFeedback(RestaurantFeedbackRequest restaurantFeedback){
        Restaurant restaurant = registeredRestaurants.get(restaurantFeedback.getRestaurantName());

        Preconditions.checkArgument(Objects.nonNull(restaurant),"No restaurant Present with name: " + restaurantFeedback.getRestaurantName());

        BigDecimal currentScore = restaurant.getCurrentScore();

        BigDecimal newScore = currentScore.add(restaurantFeedback.getFeedback().getRating());

        newScore = newScore.divide(BigDecimal.valueOf(restaurant.getFeedbacks().size() + 1));

        System.out.println("New score for restaurant: " + restaurantFeedback.getRestaurantName() + " is: " + newScore);

        restaurant.setCurrentScore(newScore);

        restaurant.getFeedbacks().add(restaurantFeedback.getFeedback());

        registeredRestaurants.put(restaurantFeedback.getRestaurantName(), restaurant);

        return true;
    }
}
