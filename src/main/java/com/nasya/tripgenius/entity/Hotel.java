package com.nasya.tripgenius.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_star")
    private Integer hotelStar;

    private String address;

    @ManyToOne
    @JoinColumn(name = "hotel_category", referencedColumnName = "id")
    private HotelCategory hotelCategory;

    @OneToMany(mappedBy = "hotel")
    private List<WishList> wishLists;

}
