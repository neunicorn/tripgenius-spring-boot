package com.nasya.tripgenius.model.user;

import org.springframework.web.multipart.MultipartFile;

import com.nasya.tripgenius.entity.UserGender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private MultipartFile profilePicture;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String username;

    @Email
    private String email;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String homeTown;

    private Integer age;

    private UserGender gender;
}
