package com.machinecoding.restaurant.model;

import com.machinecoding.restaurant.statics.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Feedback {

    public void Feedback(){
        Id = IdGenerator.getId();
    }

    private Long Id;
    private Long userId;
    private BigDecimal rating;
    private String review;
}
