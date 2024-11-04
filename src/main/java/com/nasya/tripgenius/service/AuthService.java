package com.nasya.tripgenius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import com.nasya.tripgenius.model.auth.JWTResponse;
import com.nasya.tripgenius.model.auth.LoginUserRequest;
import com.nasya.tripgenius.security.JwtProvider;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public JWTResponse login(LoginUserRequest req) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getUsername(),
                req.getPassword()));

        /*
         * 02 - SecurityContextHolder is used to allows the rest of the application to
         * know
         * that the user is authenticated and can use user data from Authentication
         * object
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtProvider.generateToken(authentication);

        return JWTResponse.builder().accessToken(token).build();
    };

}
