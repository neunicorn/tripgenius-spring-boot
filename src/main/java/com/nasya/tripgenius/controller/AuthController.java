package com.nasya.tripgenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.auth.JWTResponse;
import com.nasya.tripgenius.model.auth.LoginUserRequest;
import com.nasya.tripgenius.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public WebResponse<JWTResponse> login(@RequestBody LoginUserRequest req) {

        JWTResponse res = authService.login(req);

        return WebResponse.<JWTResponse>builder().message("LOGIN_SUCCESS").data(res).build();
    }

}
