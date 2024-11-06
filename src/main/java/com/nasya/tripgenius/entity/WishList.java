package com.nasya.tripgenius.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "wisata_fk")
    private TempatWisata tempatWisata;

    @ManyToOne
    @JoinColumn(name = "hotel_fk")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "uid_fk")
    private User user;

    @ManyToOne
    @JoinColumn(name = "transportation_fk")
    private Transportation transportation;

    @ManyToOne
    @JoinColumn(name = "restaurant_fk")
    private Restaurant restaurant;
}
