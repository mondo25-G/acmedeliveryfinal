package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;;
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
@Order(3)
public class OrderContentCreator extends BaseComponent implements CommandLineRunner {

    private final OrderService orderService;
    private final StoreService storeService;
    private final AccountService accountService;

    String addItem = "Add Item : {} ";
    String removeItem = "Remove Item : {} ";
    String updateItem = "Update Item : {} ";
    String orderComplete = "Order Completed: {} ";


    @Override
    public void run(String... args) throws InterruptedException {

        // ORDER 1 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder =
                orderService.initiateOrder(storeService.get(1L), accountService.get(1L));
        Store store1 = storeService.getLazy(1L);
        List<StoreItem> store1Items = store1.getStoreItems().stream().toList();


        // ORDER 1 - ADD ITEMS AND QUANTITY
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(1L,1L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(1L,2L), 3));
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(1L,3L), 1));
        logger.info(addItem, orderService.addItem(newOrder, storeService.getProduct(1L,4L), 1));

        // ORDER 1 - CHECKOUT OF ORDER
        orderService.checkout(newOrder, PaymentMethod.CASH);
        logger.info(orderComplete, newOrder);

        //ok


        // ORDER 2 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder2 =
                orderService.initiateOrder(storeService.get(2L),accountService.get(2L));

        // ORDER 2 - ADD ITEMS AND QUANTITY
        logger.info(addItem,orderService.addItem(newOrder2,storeService.getProduct(10L),2));
        logger.info(addItem,orderService.addItem(newOrder2,storeService.getProduct(11L),2));
        logger.info(addItem,orderService.addItem(newOrder2,storeService.getProduct(9L),2));

        // ORDER 2 - CHECKOUT OF ORDER
        orderService.checkout(newOrder2, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder2);

        // ORDER 3 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder3 =
                orderService.initiateOrder(storeService.get(2L),accountService.get(3L));

        // ORDER 3 - ADD ITEMS AND QUANTITY
        logger.info(addItem,orderService.addItem(newOrder3,storeService.getProduct(13L),3));  //In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeService.getProduct(11L),2));  // In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeService.getProduct(15L),4));   // In Store

        // ORDER 3 - CHECKOUT OF ORDER
        orderService.checkout(newOrder3, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder3);



        // ORDER 4 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder4 =
                orderService.initiateOrder(storeService.get(3L), accountService.get(3L));

        // ORDER 4 - ADD ITEMS AND QUANTITY
        logger.info(addItem, orderService.addItem(newOrder4, storeService.getProduct(19L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeService.getProduct(17L), 1));
        logger.info(addItem, orderService.addItem(newOrder4, storeService.getProduct(18L), 2));

        // ORDER 4 - CHECKOUT OF ORDER
        orderService.checkout(newOrder4, PaymentMethod.CARD);
        logger.info(orderComplete, newOrder4);


        // ORDER 5 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder5 =
                orderService.initiateOrder(storeService.get(3L),accountService.get(2L));

        // ORDER 5 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder5,storeService.getProduct(22L),1));  //In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeService.getProduct(19L),1));  // In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeService.getProduct(18L),2));   // In Store


        // ORDER 5 - CHECKOUT OF ORDER
        orderService.checkout(newOrder5, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder5);


        // ORDER 6 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder6 =
                orderService.initiateOrder(storeService.get(4L),accountService.get(3L));

        // ORDER 6 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder6,storeService.getProduct(23L),1));
        logger.info(addItem,orderService.addItem(newOrder6,storeService.getProduct(24L),2));
        logger.info(addItem,orderService.addItem(newOrder6,storeService.getProduct(25L),3));
        logger.info(addItem,orderService.addItem(newOrder6,storeService.getProduct(26L),1));

        // ORDER 6 - CHECKOUT OF ORDER
        orderService.checkout(newOrder6, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder6);


        // ORDER 6 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder7 =
                orderService.initiateOrder(storeService.get(7L),accountService.get(1L));

        // ORDER 6 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder7,storeService.getProduct(56L),1));
        logger.info(addItem,orderService.addItem(newOrder7,storeService.getProduct(57L),2));

        // ORDER 6 - CHECKOUT OF ORDER
        orderService.checkout(newOrder7, PaymentMethod.CASH);
        logger.info(orderComplete,newOrder7);

    }

}
