package com.nasya.tripgenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasya.tripgenius.entity.TempatWisata;

@Repository
public interface TempatWisataRepository extends JpaRepository<TempatWisata, Long> {

}
