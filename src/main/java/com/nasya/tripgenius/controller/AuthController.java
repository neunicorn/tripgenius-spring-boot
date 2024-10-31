package com.nasya.tripgenius.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.auth.CreateUserRequest;
import com.nasya.tripgenius.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/api/v1/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> register(@RequestBody CreateUserRequest request) {

        authService.register(request);

        return WebResponse.<String>builder().data("OK").message("USER_CREATED").build();
    }

}
