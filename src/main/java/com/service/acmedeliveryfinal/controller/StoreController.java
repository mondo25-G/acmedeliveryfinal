package com.service.acmedeliveryfinal.controller;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;
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

    @GetMapping("/getByName")
    public ResponseEntity<ApiResponse<List<Store>>> getStoresByName(@RequestParam String name) {

        final List<Store> storesByName = storeService.getStoresByName(name);
        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(storesByName).build());

    }

    @GetMapping("/getByCategory")
    public ResponseEntity<ApiResponse<List<Store>>> getStoresByCategory(@RequestParam String category) {
        StoreCategory storeCategoryEnum = StoreCategory.valueOf(category);
        final List<Store> storesByCategory = storeService.getStoresByCategory(storeCategoryEnum);
        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(storesByCategory).build());

    }
}