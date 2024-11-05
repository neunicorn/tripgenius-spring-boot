package com.nasya.tripgenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.user.ChangePasswordRequest;
import com.nasya.tripgenius.model.user.CreateUserRequest;
import com.nasya.tripgenius.model.user.UpdateUserRequest;
import com.nasya.tripgenius.model.user.UpdateUserResponse;
import com.nasya.tripgenius.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/v1/user/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> create(@RequestBody CreateUserRequest request) {

        userService.create(request);

        return WebResponse.<String>builder().data("OK").message("USER_CREATED").build();
    };

    @PutMapping(path = "/api/v1/user/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> updatePassword(Authentication auth, @RequestBody ChangePasswordRequest req) {

        String username = auth.getName();

        log.info("the username is " + username);

        userService.updatePassword(req, username);

        return WebResponse.<String>builder().data("OK").message("UPDATE PASSWORD SUCCESS").build();
    }

    @PutMapping(path = "/api/v1/user/updateProfile")
    public WebResponse<UpdateUserResponse> updateProfile(Authentication auth, @RequestBody UpdateUserRequest req) {

        UpdateUserResponse res = userService.updateProfile(auth.getName(), req);

        return WebResponse.<UpdateUserResponse>builder().data(res).message("UPDATE_PROFILE_SUCCESS").build();
    }

}
