package com.machinecoding.restaurant.controller;

import com.machinecoding.restaurant.model.Restaurant;
import com.machinecoding.restaurant.request.RestaurantFeedbackRequest;
import com.machinecoding.restaurant.request.RestaurantRegistrationRequest;
import com.machinecoding.restaurant.request.UpdateRestaurantDetailsRequest;
import com.machinecoding.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("register")
    public ResponseEntity<Restaurant> registerRestaurant(RestaurantRegistrationRequest restaurantRegistrationRequest) {
        return ResponseEntity.ok(restaurantService.registerRestaurant(restaurantRegistrationRequest));
    }

    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurantDetails(UpdateRestaurantDetailsRequest updateRestaurantDetailsRequest) {
        return ResponseEntity.ok(restaurantService.updateRestaurantDetails(updateRestaurantDetailsRequest));
    }

    public ResponseEntity<Boolean> registerFeedback(RestaurantFeedbackRequest restaurantFeedbackRequest){
        return ResponseEntity.ok(restaurantService.registerFeedback(restaurantFeedbackRequest));
    }
}
