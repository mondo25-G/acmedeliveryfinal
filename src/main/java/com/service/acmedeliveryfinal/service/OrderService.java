package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.transfer.AccountOrderHeaderDto;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import com.service.acmedeliveryfinal.transfer.OrderDetailsDto;

import java.util.List;


public interface OrderService extends BaseService<Order> {
    Order initiateOrder(Store store, Account account);

    //Add new Item to Order
    Order addItem(Order order, StoreItem item, int quantity);

    //Update the Item in the Order
    Order updateItem(Order order, StoreItem item, int quantity);

    //Remove Item from Order
    Order removeItem(Order order, StoreItem item);

    //Completion of Order and Creation
    Order checkout(Order order, PaymentMethod paymentMethod);

    Order getLazy(Long id);

    List<Order> getAllByAccount(Long id);
    List<KeyValue<AccountOrderHeaderDto, List<OrderDetailsDto>>> findOrdersByAccount(Long accountId);
}
