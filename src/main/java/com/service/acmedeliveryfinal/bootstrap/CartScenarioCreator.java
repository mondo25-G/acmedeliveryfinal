package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.OrderService;
import com.service.acmedeliveryfinal.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(5)
public class CartScenarioCreator extends BaseComponent implements CommandLineRunner {

    private final OrderService orderService;
    private final StoreService storeService;
    private final AccountService accountService;

    String addItem = "Add Item : {} ";
    String removeItem = "Remove Item : {} ";
    String updateItem = "Update Item : {} ";
    String orderComplete = "Order Completed: {} ";

    @Override
    public void run(String... args) throws Exception {

        logger.info("cart scenario start:");

        com.service.acmedeliveryfinal.domain.Order newOrder =
                orderService.initiateOrder(storeService.get(5L), accountService.get(3L));
        // ORDER 1 - ADD ITEMS AND UPDATE QUANTITIES NOT BELOW ZERO
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(5L,33L), 3));
        logger.info(updateItem,orderService.updateItem(newOrder,storeService.getProduct(33L),1));
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(5L,32L), 3));
        logger.info(updateItem,orderService.updateItem(newOrder,storeService.getProduct(32L),5));
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(5L,36L), 1));
        logger.info(updateItem,orderService.updateItem(newOrder,storeService.getProduct(36L),2));
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(5L,40L), 1));
        // ORDER 1 - CHECKOUT OF ORDER
        orderService.checkout(newOrder, PaymentMethod.CASH);
        logger.info(orderComplete, newOrder);

        com.service.acmedeliveryfinal.domain.Order newOrder1 =
                orderService.initiateOrder(storeService.get(5L), accountService.get(2L));
        // ORDER 2 - ADD ITEMS AND REMOVE ITEMS
        logger.info(addItem, orderService.addItem(newOrder1, storeService.getProduct(5L,34L), 3));
        logger.info(addItem, orderService.addItem(newOrder1, storeService.getProduct(5L,35L), 1));
        logger.info(updateItem,orderService.updateItem(newOrder1,storeService.getProduct(32L),3));
        logger.info(addItem, orderService.addItem(newOrder1, storeService.getProduct(42L), 1));
        logger.info(removeItem,orderService.removeItem(newOrder1,storeService.getProduct(34L)));
        // ORDER 2 - CHECKOUT OF ORDER
        orderService.checkout(newOrder1, PaymentMethod.CASH);
        logger.info(orderComplete, newOrder1);


        com.service.acmedeliveryfinal.domain.Order newOrder2 =
                orderService.initiateOrder(storeService.get(5L), accountService.get(1L));
        // ORDER 3 - ADD ITEMS AND ADD ITEM FROM ANOTHER STORE - EMPTY CART THEN ADD
        logger.info(addItem, orderService.addItem(newOrder2, storeService.getProduct(5L,37L), 1));
        logger.info(addItem, orderService.addItem(newOrder2, storeService.getProduct(5L,38L), 2));
        logger.info(addItem, orderService.addItem(newOrder2, storeService.getProduct(6L,44L), 2));

        // ORDER 3 - CHECKOUT OF ORDER
        orderService.checkout(newOrder2, PaymentMethod.CASH);
        logger.info(orderComplete, newOrder2);

    }
}
