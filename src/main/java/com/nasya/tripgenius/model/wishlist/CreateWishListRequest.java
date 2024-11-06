package com.nasya.tripgenius.model.wishlist;

import com.nasya.tripgenius.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWishListRequest {

    private Long wisataFk;

    private Long hotelFk;

    private Long transportationFk;

    private Long restaurantFk;

    private Status status;

}
