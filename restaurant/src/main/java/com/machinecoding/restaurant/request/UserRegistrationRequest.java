package com.machinecoding.restaurant.request;

import com.machinecoding.restaurant.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String pincode;
}
