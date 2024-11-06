package com.nasya.tripgenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasya.tripgenius.entity.WishList;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long> {

}
