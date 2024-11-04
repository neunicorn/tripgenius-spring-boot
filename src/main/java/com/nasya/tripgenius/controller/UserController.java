package com.nasya.tripgenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.user.CreateUserRequest;
import com.nasya.tripgenius.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/v1/user/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> create(@RequestBody CreateUserRequest request) {

        userService.create(request);

        return WebResponse.<String>builder().data("OK").message("USER_CREATED").build();
    }

}
