package com.service.acmedeliveryfinal.controller;


import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.service.StoreItemService;
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
@RequestMapping("products")
public class StoreItemController {

    private final StoreItemService storeItemService;

    //CRUD

    @GetMapping("/getTopProductsByRanking")
    public ResponseEntity<ApiResponse<List<StoreItem>>> getTopProductsByRanking(@RequestParam Integer rankingThreshold) {

        final List<StoreItem> topProductsByRanking = storeItemService.topProductsByRanking(rankingThreshold);
        return ResponseEntity.ok(ApiResponse.<List<StoreItem>>builder().data(topProductsByRanking).build());

    }
}
