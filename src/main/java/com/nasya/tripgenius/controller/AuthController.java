package com.nasya.tripgenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.auth.JWTResponse;
import com.nasya.tripgenius.model.auth.LoginUserRequest;
import com.nasya.tripgenius.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/api/v1/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<JWTResponse> login(@RequestBody LoginUserRequest req) {

        JWTResponse res = authService.login(req);

        return WebResponse.<JWTResponse>builder().message("LOGIN_SUCCESS").data(res).build();
    }

}
