package com.machinecoding.restaurant.model;

import com.machinecoding.restaurant.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String pincode;
}
