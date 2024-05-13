package com.machinecoding.restaurant.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRestaurantDetailsRequest {

    private String restaurantName;
    private Integer quantityToAdd;
}
