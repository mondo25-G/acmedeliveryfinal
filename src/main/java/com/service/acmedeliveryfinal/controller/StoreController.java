package com.service.acmedeliveryfinal.controller;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
@PreAuthorize("hasRole('USER')")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StoreDetailsDto>>>getStores() {

        return ResponseEntity.ok(ApiResponse.<List<StoreDetailsDto>>builder().data(storeService.getStoreDetailsDtos()).build());
    }

    //Get the store and its catalogue
    @GetMapping("/{id}/products")
    public ResponseEntity<ApiResponse<StoreDto>> findProductsByStore(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<StoreDto>builder().data(storeService.getStoreDto(id)).build());
    }

    //Get store categories
    @GetMapping("/storeCategories")
    public ResponseEntity<ApiResponse<List<StoreCategory>>> getStoreCategories(){

        return ResponseEntity.ok(ApiResponse.<List<StoreCategory>>builder().data(storeService.getAllStoreCategories()).build());
    }

    @GetMapping("search")
    public ResponseEntity<ApiResponse<List<KeyValue<Long, String>>>> search(@RequestParam String searchString){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long, String>>>builder().data(storeService.getStoresDropdownList(searchString)).build());
    }

    //get stores by category id
    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<List<Store>>> byCategory(@PathVariable("id") final Long id){
        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(storeService.getStoresByCategoryId(id)).build());
    }

    //Endpoint to get top stores
    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTop(){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long,String>>>builder().data(storeService.findPopularStores()).build());
    }

    //Endpoint to get top products
    @GetMapping("/popular/products")
    public ResponseEntity<ApiResponse<List<PopularItemDto>>> findTopProducts(){
        return ResponseEntity.ok(ApiResponse.<List<PopularItemDto>>builder().data(storeService.findPopularProducts()).build());
    }

    //Endpoint to get top stores by Category id (Long)
    @GetMapping("/popular/categories/{id}")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTopByCategoryId(@PathVariable("id") final Long id){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long,String>>>builder().data(storeService.findPopularStoresByCategory(id)).build());
    }


}