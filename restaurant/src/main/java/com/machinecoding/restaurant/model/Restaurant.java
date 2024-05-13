package com.machinecoding.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restaurant {

    private String restaurantName;
    private Set<String> serviceablePincodes;
    private String foodItemName;
    private Long foodItemPrice;
    private Integer quantity;
    private User owner;
    private List<String> reviews;
    private List<Feedback> feedbacks;
    private BigDecimal currentScore;
}
