package com.nasya.tripgenius.model.user;

import com.nasya.tripgenius.entity.UserGender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String name;

    private String username;

    private String email;

    private String phone;

    private String homeTown;

    private Integer age;

    private UserGender gender;

    private String profilePicture;
}
