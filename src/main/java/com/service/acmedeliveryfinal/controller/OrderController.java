package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.service.BaseService;
import com.service.acmedeliveryfinal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController extends BaseController<Order> {

    private final OrderService orderService;

    @Override
    protected BaseService<Order> getBaseService() {
        return orderService;
    }
}
