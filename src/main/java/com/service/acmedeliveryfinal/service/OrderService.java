package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;

public interface OrderService extends BaseService<Order> {
    Order initiateOrder(Store store, Account account);

    //Add new Item to Order
    Order addItem(Order order, StoreItem item, int quantity);

    //Update the Item in the Order
    Order updateItem(Order order, StoreItem item, int quantity);

    //Remove Item from Order
    Order removeItem(Order order, StoreItem item);
}
