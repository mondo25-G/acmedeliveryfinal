package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.OrderService;
import com.service.acmedeliveryfinal.service.StoreItemService;
import com.service.acmedeliveryfinal.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Order(3)
public class OrderContentCreator extends BaseComponent implements CommandLineRunner {

    private final OrderService orderService;
    private final StoreService storeService;
    private final StoreItemService storeItemService;
    private final AccountService accountService;

    String addItem = "Add Item : {} ";
    String removeItem = "Remove Item : {} ";
    String updateItem = "Update Item : {} ";
    String orderComplete = "Order Completed: {} ";


    @Override
    public void run(String... args)  {

        // ORDER 1 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder =
                orderService.initiateOrder(storeService.get(1L), accountService.get(1L));

        // ORDER 1 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEMS IN STORE
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(1L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(2L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(3L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(4L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(10L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(4L), 2));
        logger.info(addItem, orderService.addItem(newOrder, storeItemService.get(9L), 2));

        // ORDER 1 -REMOVE AND UPDATE ITEMS WITH NEW QUANTITY
        logger.info(removeItem, orderService.removeItem(newOrder, storeItemService.get(4L)));
        logger.info(updateItem, orderService.updateItem(newOrder, storeItemService.get(2L), 3));

        // ORDER 1 - CHECKOUT OF ORDER
        orderService.checkout(newOrder, PaymentMethod.CASH);
        logger.info(orderComplete, newOrder);


        // ORDER 2 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder2 =
                orderService.initiateOrder(storeService.get(2L),accountService.get(2L));

        // ORDER 2 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(1L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(8L),2));   //Not In Store
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(10L),2));  //In Store
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(11L),2));  // In Store
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(4L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(9L),2));   // In Store
        logger.info(addItem,orderService.addItem(newOrder2,storeItemService.get(6L),2));   // Not In Store

        // ORDER 2 - REMOVE AND UPDATE ITEMS WITH NEW QUANTITY
        logger.info(removeItem,orderService.removeItem(newOrder2,storeItemService.get(9L)));
        logger.info(updateItem,orderService.updateItem(newOrder2,storeItemService.get(10L),3));

        // ORDER 2 - CHECKOUT OF ORDER
        orderService.checkout(newOrder2, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder2);


        // ORDER 3 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder3 =
                orderService.initiateOrder(storeService.get(3L),accountService.get(3L));

        // ORDER 3 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(15L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(14L),2));   //Not In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(17L),2));  //In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(16L),2));  // In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(10L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(18L),2));   // In Store
        logger.info(addItem,orderService.addItem(newOrder3,storeItemService.get(9L),2));   // Not In Store

        // ORDER 3 - REMOVE AND UPDATE ITEMS WITH NEW QUANTITY
        logger.info(removeItem,orderService.removeItem(newOrder3,storeItemService.get(17L)));
        logger.info(updateItem,orderService.updateItem(newOrder3,storeItemService.get(18L),3));

        // ORDER 3 - CHECKOUT OF ORDER
        orderService.checkout(newOrder3, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder3);


        // ORDER 4 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder4 =
                orderService.initiateOrder(storeService.get(1L), accountService.get(1L));

        // ORDER 4 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEMS IN STORE
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(1L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(2L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(3L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(4L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(10L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(4L), 2));
        logger.info(addItem, orderService.addItem(newOrder4, storeItemService.get(9L), 2));

        // ORDER 4 -REMOVE AND UPDATE ITEMS WITH NEW QUANTITY
        logger.info(removeItem, orderService.removeItem(newOrder4, storeItemService.get(4L)));
        logger.info(updateItem, orderService.updateItem(newOrder4, storeItemService.get(2L), 3));

        // ORDER 4 - CHECKOUT OF ORDER
        orderService.checkout(newOrder, PaymentMethod.CASH);
        logger.info(orderComplete, newOrder);


        // ORDER 5 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder5 =
                orderService.initiateOrder(storeService.get(2L),accountService.get(2L));

        // ORDER 5 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(1L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(8L),2));   //Not In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(10L),2));  //In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(11L),2));  // In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(4L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(9L),2));   // In Store
        logger.info(addItem,orderService.addItem(newOrder5,storeItemService.get(6L),2));   // Not In Store

        // ORDER 5 - REMOVE AND UPDATE ITEMS WITH NEW QUANTITY
        logger.info(removeItem,orderService.removeItem(newOrder5,storeItemService.get(9L)));
        logger.info(updateItem,orderService.updateItem(newOrder5,storeItemService.get(10L),3));

        // ORDER 5 - CHECKOUT OF ORDER
        orderService.checkout(newOrder5, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder5);


        // ORDER 6 - INITIALIZE WITH STORE AND ACCOUNT
        com.service.acmedeliveryfinal.domain.Order newOrder6 =
                orderService.initiateOrder(storeService.get(3L),accountService.get(3L));

        // ORDER 6 - ADD ITEMS AND QUANTITY , TEST NON EXISTING ITEM IN STORE
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(15L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(14L),2));   //Not In Store
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(17L),2));  //In Store
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(16L),2));  // In Store
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(10L),2));   // Not In Store
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(18L),2));   // In Store
        logger.info(addItem,orderService.addItem(newOrder6,storeItemService.get(9L),2));   // Not In Store

        // ORDER 6 - REMOVE AND UPDATE ITEMS WITH NEW QUANTITY
        logger.info(removeItem,orderService.removeItem(newOrder6,storeItemService.get(17L)));
        logger.info(updateItem,orderService.updateItem(newOrder6,storeItemService.get(18L),3));

        // ORDER 6 - CHECKOUT OF ORDER
        orderService.checkout(newOrder6, PaymentMethod.CARD);
        logger.info(orderComplete,newOrder6);
    }
}
