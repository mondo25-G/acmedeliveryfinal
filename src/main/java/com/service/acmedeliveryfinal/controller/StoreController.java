package com.service.acmedeliveryfinal.controller;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
public class StoreController {

    private final StoreService storeService;

    //CRUD


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Store>>getStore(@PathVariable("id") final Long id ) {

        return ResponseEntity.ok(ApiResponse.<Store>builder().data(storeService.getLazy(id)).build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Store>>>getStores() {

        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(storeService.getLazyAll()).build());

    }

    @GetMapping("/storeCategories")
    public ResponseEntity<ApiResponse<List<StoreCategory>>> getStoreCategories(){

        return ResponseEntity.ok(ApiResponse.<List<StoreCategory>>builder().data(storeService.getAllStoreCategories()).build());
    }

    @GetMapping("search")
    public ResponseEntity<ApiResponse<List<KeyValue<Long, String>>>> search(@RequestParam String searchString){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long, String>>>builder().data(storeService.getStoresDropdownList(searchString)).build());
    }

    //getStoresByCategory
    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<List<Store>>> byCategory(@PathVariable("id") final Long id){
        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(storeService.getStoresByCategoryId(id)).build());
    }

    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTop(){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long,String>>>builder().data(storeService.findPopularStores()).build());
    }

    //endpoint to get top stores by Category id (Long)
    @GetMapping("/popular/categories/{id}")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTopByCategoryId(@PathVariable("id") final Long id){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long,String>>>builder().data(storeService.findPopularStoresByCategory(id)).build());
    }


    //enpoint to get top stores by Category name (string)
    @GetMapping("/popular/categories")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTopByCategoryName(@RequestParam("name") String categoryName){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long,String>>>builder().data(storeService.findPopularStoresByCategory(categoryName)).build());
    }

    //possible endpoint to get top products
    @GetMapping("/popular/products")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTopProducts(){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long, String>>>builder().data(storeService.findPopularProducts()).build());
    }

}