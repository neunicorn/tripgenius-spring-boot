package com.nasya.tripgenius.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserResponse {

    private String profilePicture;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String homeTown;

}
