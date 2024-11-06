package com.nasya.tripgenius.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.nasya.tripgenius.entity.User;
import com.nasya.tripgenius.model.user.ChangePasswordRequest;
import com.nasya.tripgenius.model.user.CreateUserRequest;
import com.nasya.tripgenius.model.user.UpdateUserRequest;
import com.nasya.tripgenius.model.user.UserResponse;
import com.nasya.tripgenius.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Transactional
    public void create(CreateUserRequest req) {

        validationService.validate(req);

        if (userRepository.existsByUsernameOrEmail(req.getUsername(), req.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EMAIL_OR_USERNAME_ALREADY_USED");
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

    @Transactional
    public void updatePassword(ChangePasswordRequest request, String username) {

        validationService.validate(request);

        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "USERNAME NOT FOUND"));

        if (BCrypt.checkpw(request.getCurrentPassword(), user.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt()));
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    public UserResponse updateProfile(String username, UpdateUserRequest req) {

        validationService.validate(req);

        // fetch data user
        User user = userRepository.findFirstByUsername(username).get();

        if (Objects.nonNull(req.getName())) {
            user.setName(req.getName());
        }

        if (Objects.nonNull(req.getUsername())) {
            if (userRepository.findFirstByUsername(req.getUsername()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USERNAME CANNOT BE USED");
            }
            user.setUsername(req.getUsername());
        }

        if (Objects.nonNull(req.getEmail())) {
            if (userRepository.findFirstByEmail(req.getEmail()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EMAIL CANNOT BE USED");
            }
            user.setEmail(req.getEmail());
        }

        if (Objects.nonNull(req.getPhone())) {
            if (userRepository.findFirstByPhone(req.getPhone()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PHONE_NUMBER_ALREADY_USED");
            }
            user.setEmail(req.getPhone());
        }

        if (Objects.nonNull(req.getHomeTown())) {
            user.setHomeTown(req.getHomeTown());
        }

        if (Objects.nonNull(req.getProfilePicture())) {
            user.setProfilePicture(cloudinaryService.uploadFile(req.getProfilePicture(), "folder_1"));
        }
        userRepository.save(user);

        if (Objects.nonNull(req.getAge())) {
            user.setAge(req.getAge());
        }

        if (Objects.nonNull(req.getGender())) {
            user.setGender(req.getGender());
        }

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .phone(user.getPhone())
                .homeTown(user.getHomeTown())
                .gender(user.getGender())
                .age(user.getAge())
                .profilePicture(user.getProfilePicture())
                .build();
    }

    @Transactional(readOnly = true)
    public UserResponse get(String username) {
        User user = userRepository.findFirstByUsername(username).get();

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .homeTown(user.getHomeTown())
                .gender(user.getGender())
                .age(user.getAge())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}
