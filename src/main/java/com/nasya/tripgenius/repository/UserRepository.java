package com.nasya.tripgenius.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasya.tripgenius.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findFirstByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findFirstByPhone(String phone);

}
