package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.BaseService;
import com.service.acmedeliveryfinal.service.OrderService;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.AccountOrderHeaderDto;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import com.service.acmedeliveryfinal.transfer.OrderDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController extends BaseController<Order> {

    private final OrderService orderService;

    private final StoreService storeService;


    private Order newOrder;

    @Override
    protected BaseService<Order> getBaseService() {
        return orderService;
    }


    @GetMapping("/initiate")
    public ResponseEntity<ApiResponse<Order>> initiate(@RequestParam Store store, Account account){
        newOrder= orderService.initiateOrder(store,account);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @GetMapping("/addItem")
    public ResponseEntity<ApiResponse<Order>> addItem(@RequestParam Long id , int quantity){
        StoreItem item = storeService.getProduct(id);
        newOrder= orderService.addItem(newOrder, item, quantity);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @PatchMapping("/updateItem")
    public ResponseEntity<ApiResponse<Order>> updateItem(@RequestParam Long id , int quantity){
        StoreItem item = storeService.getProduct(id);
        newOrder= orderService.updateItem(newOrder, item, quantity);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<ApiResponse<Order>> deleteItem(@RequestParam Long id ){
        StoreItem item = storeService.getProduct(id);
        newOrder= orderService.removeItem(newOrder, item);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @GetMapping("/checkout")
    public ResponseEntity<ApiResponse<Order>> checkout(PaymentMethod paymentMethod){
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(orderService.checkout(newOrder, paymentMethod)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(orderService.getLazy(id)).build());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<ApiResponse<List<Order>>> getOrderList(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<List<Order>>builder().data(orderService.getAllByAccount(id)).build());
    }

    @GetMapping("/accountDto/{id}")
    public ResponseEntity<ApiResponse<List<KeyValue<AccountOrderHeaderDto, List<OrderDetailsDto>>>>> getOrdersByAccount(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<AccountOrderHeaderDto, List<OrderDetailsDto>>>>builder().data(orderService.findOrdersByAccount(id)).build());
    }

}
