package com.nasya.tripgenius.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nasya.tripgenius.entity.Hotel;
import com.nasya.tripgenius.entity.Restaurant;
import com.nasya.tripgenius.entity.Status;
import com.nasya.tripgenius.entity.TempatWisata;
import com.nasya.tripgenius.entity.Transportation;
import com.nasya.tripgenius.entity.User;
import com.nasya.tripgenius.entity.WishList;
import com.nasya.tripgenius.model.wishlist.CreateWishListRequest;
import com.nasya.tripgenius.repository.HotelRepository;
import com.nasya.tripgenius.repository.RestaurantRepository;
import com.nasya.tripgenius.repository.TempatWisataRepository;
import com.nasya.tripgenius.repository.TransportationRepository;
import com.nasya.tripgenius.repository.UserRepository;
import com.nasya.tripgenius.repository.WishlistRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TempatWisataRepository tempatWisataRepository;

    @Autowired
    private TransportationRepository transportationRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ValidationService validationService;

    public void create(String username, CreateWishListRequest req) {

        log.info("Request + " + req.toString());

        log.info("REQUST HIT!");
        if (Objects.isNull(req)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
        }
        validationService.validate(req);

        log.info("MAKING WISHLIST");
        WishList wishList = new WishList();

        if (Objects.nonNull(username)) {
            log.info("username not null");
            User user = userRepository.findFirstByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "USER NOT FOUND"));
            wishList.setUser(user);
        }

        if (Objects.nonNull(req.getHotelFk())) {
            log.info("hotel hit");
            Hotel hotel = hotelRepository.findById(req.getHotelFk())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "HOTEL NOT FOUND"));

            log.info("data fetched");
            wishList.setHotel(hotel);
        }

        if (Objects.nonNull(req.getRestaurantFk())) {
            Restaurant restaurant = restaurantRepository.findById(req.getRestaurantFk())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "RESTAURANT NOT FOUND"));
            wishList.setRestaurant(restaurant);
        }

        if (Objects.nonNull(req.getWisataFk())) {
            log.info("tempat hit");
            TempatWisata tempatWisata = tempatWisataRepository.findById(req.getWisataFk())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "TEMPAT_WISATA NOT FOUND"));

            log.info("tempatwisata fethced");
            wishList.setTempatWisata(tempatWisata);
        }

        if (Objects.nonNull(req.getTransportationFk())) {
            Transportation transportation = transportationRepository.findById(req.getTransportationFk())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "TRANSPORTATION NOT FOUND"));
            wishList.setTransportation(transportation);
        }

        wishList.setStatus(Status.True);

        wishlistRepository.save(wishList);

    }

}
