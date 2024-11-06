package com.nasya.tripgenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasya.tripgenius.entity.Transportation;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {

}
