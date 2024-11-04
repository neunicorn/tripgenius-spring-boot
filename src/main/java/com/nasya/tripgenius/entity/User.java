package com.nasya.tripgenius.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String phone;

    @Column(name = "home_town")
    private String homeTown;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    private String location;

    @Column(name = "profile_picture")
    private String profilePicture;

    @OneToOne
    @JoinColumn(name = "profilecheck", unique = true)
    private ProfileCheck profileCheck;

    @OneToMany(mappedBy = "user")
    private List<WishList> wishLists;
}
