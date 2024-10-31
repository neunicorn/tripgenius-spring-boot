package com.nasya.tripgenius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.nasya.tripgenius.entity.User;
import com.nasya.tripgenius.model.auth.CreateUserRequest;
import com.nasya.tripgenius.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(CreateUserRequest req) {

        validationService.validate(req);

        boolean isEmailAlreadyUsed = userRepository.findFirstByEmail(req.getEmail()).isPresent();
        if (isEmailAlreadyUsed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EMAIL ALREADY USED");
        }

        boolean isUsernameAlreadyUsed = userRepository.findFirstByUsername(req.getUsername()).isPresent();
        if (isUsernameAlreadyUsed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USERNAME_ALREADY_USED");
        }

        boolean isPhoneAlreadyUsed = userRepository.findFirstByPhone(req.getPhone()).isPresent();
        if (isPhoneAlreadyUsed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PHONE_NUMBER_ALREADY_USED");
        }

        User user = new User();
        user.setName(req.getName());
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
        user.setPhone(req.getPhone());
        user.setHomeTown(req.getHomeTown());
        userRepository.save(user);

    }

}
