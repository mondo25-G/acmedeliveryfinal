package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Product;
import com.service.acmedeliveryfinal.service.ProductServiceImpl;
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
public class ProductController {

    private final ProductServiceImpl productService;

    //CRUD

    @GetMapping("/getTopProductsByRanking")
    public ResponseEntity<ApiResponse<List<Product>>> getTopProductsByRanking(@RequestParam Integer rankingThreshold) {

        final List<Product> topProductsByRanking = productService.topProductsByRanking(rankingThreshold);
        return ResponseEntity.ok(ApiResponse.<List<Product>>builder().data(topProductsByRanking).build());

    }
}
