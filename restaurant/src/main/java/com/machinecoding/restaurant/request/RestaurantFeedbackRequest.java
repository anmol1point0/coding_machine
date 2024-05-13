package com.machinecoding.restaurant.request;

import com.machinecoding.restaurant.dao.UserDao;
import com.machinecoding.restaurant.model.Feedback;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RestaurantFeedbackRequest {

    public void RestaurantFeedback(){
        feedback.setUserId(UserDao.loggedInUser.getUserId());
    }

    private String restaurantName;
    private Feedback feedback;
}
