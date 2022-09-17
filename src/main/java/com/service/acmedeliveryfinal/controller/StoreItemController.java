package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.service.StoreItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class StoreItemController {

    private final StoreItemService storeItemService;

}
