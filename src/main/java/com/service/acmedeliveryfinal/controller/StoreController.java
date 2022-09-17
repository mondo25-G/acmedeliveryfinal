package com.service.acmedeliveryfinal.controller;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
public class StoreController {

    private final StoreService storeService;

    //CRUD


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Store>>getStore( Long id ) {

        return ResponseEntity.ok(ApiResponse.<Store>builder().data(storeService.getLazy(id)).build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Store>>>getStores( ) {

        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(storeService.getLazyAll()).build());

    }

}