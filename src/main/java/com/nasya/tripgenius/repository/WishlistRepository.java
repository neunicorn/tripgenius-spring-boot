package com.nasya.tripgenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasya.tripgenius.entity.User;
import com.nasya.tripgenius.entity.WishList;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUserAndStatus(User user, Boolean status);

    Optional<WishList> findFirstById(Long id);
}
