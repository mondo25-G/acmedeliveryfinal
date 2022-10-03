package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.service.BaseService;
import com.service.acmedeliveryfinal.service.OrderService;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import com.service.acmedeliveryfinal.transfer.OrderDto;
import com.service.acmedeliveryfinal.transfer.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@PreAuthorize("hasRole('USER')")
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
        logger.info("Initiating Order {} {}",store,account);
        newOrder= orderService.initiateOrder(store,account);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @PatchMapping("/addItem")
    public ResponseEntity<ApiResponse<Order>> addItem(@RequestParam String id , int quantity){
        StoreItem item = storeService.getProduct(Long.valueOf(id));
        newOrder= orderService.addItem(newOrder, item, quantity);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @PatchMapping("/updateItem")
    public ResponseEntity<ApiResponse<Order>> updateItem(@RequestParam String id , int quantity){
        StoreItem item = storeService.getProduct(Long.valueOf(id));
        newOrder= orderService.updateItem(newOrder, item, quantity);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<ApiResponse<Order>> deleteItem(@RequestParam String id ){
        StoreItem item = storeService.getProduct(Long.valueOf(id));
        newOrder= orderService.removeItem(newOrder, item);
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(newOrder).build());
    }

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<Order>> checkout(@RequestParam String paymentMethod){
        logger.info("Checkout : {}", paymentMethod);
        Order order = orderService.checkout(newOrder,PaymentMethod.valueOf(paymentMethod));
        newOrder=null;
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(order).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<Order>builder().data(orderService.getLazy(id)).build());
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<ApiResponse<List<OrderDto>>>getOrdersByAccount(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<List<OrderDto>>builder().data(orderService.findOrdersByAccount(id)).build());
    }

}
