package com.nasya.tripgenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.wishlist.CreateWishListRequest;
import com.nasya.tripgenius.service.WishListService;

@RestController
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping(path = "/api/v1/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> create(Authentication auth, @RequestBody CreateWishListRequest req) {

        String uid = auth.getName();

        wishListService.create(uid, req);

        return WebResponse.<String>builder().data("OK").message("ADD_LIST_SUCCESS").build();
    }

}
