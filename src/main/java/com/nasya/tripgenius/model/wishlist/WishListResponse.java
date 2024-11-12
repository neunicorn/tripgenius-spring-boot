package com.nasya.tripgenius.model.wishlist;

import com.nasya.tripgenius.entity.Hotel;
import com.nasya.tripgenius.entity.Restaurant;
import com.nasya.tripgenius.entity.TempatWisata;
import com.nasya.tripgenius.entity.Transportation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListResponse {

    private TempatWisata destinasi;

    private Hotel hotel;

    private Transportation transportation;

    private Restaurant restaurant;

    private Boolean status;

}
