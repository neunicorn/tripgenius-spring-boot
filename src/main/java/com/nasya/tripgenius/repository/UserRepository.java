package com.nasya.tripgenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasya.tripgenius.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
