package com.nasya.tripgenius.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nasya.tripgenius.model.WebResponse;
import com.nasya.tripgenius.model.wishlist.CreateWishListRequest;
import com.nasya.tripgenius.model.wishlist.WishListResponse;
import com.nasya.tripgenius.service.WishListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping(path = "/api/v1/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<WishListResponse>> get(Authentication auth) {

        List<WishListResponse> res = wishListService.get(auth.getName());

        return WebResponse.<List<WishListResponse>>builder().data(res).message("FETCH_LIST_DATA_SUCCESS").build();
    };

    @GetMapping(path = "/api/v1/list/{listId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<WishListResponse> getDetail(@PathVariable("listId") Long listId) {

        WishListResponse res = wishListService.getDetail(listId);

        return WebResponse.<WishListResponse>builder().data(res).message("DATA FETCHED").build();
    }

}
