package com.machinecoding.restaurant.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantRegistrationRequest {
    private String restaurantName;
    private Set<String> serviceablePincodes;
    private String foodItemName;
    private Long foodItemPrice;
    private Integer quantity;
}
