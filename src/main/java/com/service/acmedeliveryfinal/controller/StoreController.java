package com.service.acmedeliveryfinal.controller;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
public class StoreController {

    private final StoreService storeService;

    //CRUD

  /*  @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreDetailsDto>>getStore(@PathVariable("id") final Long id ) {

        return ResponseEntity.ok(ApiResponse.<StoreDetailsDto>builder().data(storeService.getStoreDetailsDto(id)).build());

    } */


    @GetMapping
    public ResponseEntity<ApiResponse<List<StoreDetailsDto>>>getStores() {

        return ResponseEntity.ok(ApiResponse.<List<StoreDetailsDto>>builder().data(storeService.getStoreDetailsDtos()).build());

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

    //Test endpoint that returns map of popularStores, to be used in conjuction with map
    @GetMapping("/popularMap")
    public ResponseEntity<ApiResponse<Map<Long,String>>> findTopMap(){
        return ResponseEntity.ok(ApiResponse.<Map<Long,String>>builder().data(storeService.findPopularStoresMap()).build());
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

    @GetMapping("/popular/productsByStore")
    public ResponseEntity<ApiResponse<List<KeyValue<Long,String>>>> findTopProductsByStore(@RequestParam("storeId") Long id){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Long, String>>>builder().data(storeService.findPopularProductsByStore(id)).build());
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<ApiResponse<StoreDto>> findProductsByStore(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<StoreDto>builder().data(storeService.getStoreDto(id)).build());
    }

}