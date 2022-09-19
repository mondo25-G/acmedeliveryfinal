package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.*;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }


    //Methods for the Initiation, Update , and Creation of Order

    @Override
    public Order initiateOrder(Store store, Account account) {

        if (account == null){throw new IllegalStateException("Customer cannot be null");}
        logger.info("Initiating Order: {} {} " , account,store);
        return Order.builder().account(account).store(store).orderItems(new HashSet<>()).build();

    }

    //Add new Item to Order
    @Override
    public Order addItem(Order order, StoreItem item, int quantity) {

        if (checkNullability(order, item)) {return order;}

        if(checkStore(order, item)) {return order;}

        boolean increasedQuantity = false;

        // If product is already contained in the order, don't add it again, just increase the quantity accordingly
        for (OrderItem oi : order.getOrderItems()) {
            if (oi.getStoreItem().getId().equals(item.getId())) {
                oi.setQuantity(oi.getQuantity() + quantity);
                increasedQuantity = true;
                break;
            }
        }

        if (!increasedQuantity) {
            order.getOrderItems().add(newOrderItem(order, item, quantity));
        }

        logger.debug("Product[{}] added to Order[{}]", item, order);
        return order;
    }



    //Update the Item in the Order
    @Override
    public Order updateItem(Order order, StoreItem item, int quantity) {
        if (checkNullability(order, item)) {return order;}
        if(checkStore(order, item)) {return order;}

        if (quantity<1){
            removeItem(order, item);
            return order;
        }

        order.getOrderItems().removeIf(oi -> oi.getStoreItem().getId().equals(item.getId()));
        order.getOrderItems().add(newOrderItem(order, item, quantity));

        logger.debug("Product[{}] updated in Order[{}]", item, order);
        return order;
    }

    //Remove Item from Order
    @Override
    public Order removeItem(Order order, StoreItem item) {
        if (checkNullability(order, item)) {return order;}
        if(checkStore(order, item)) {return order;}

        order.getOrderItems().removeIf(oi -> oi.getStoreItem().getId().equals(item.getId()));
        logger.debug("Product[{}] removed from Order[{}]", item, order);
        return order;
    }

    //Completion of Order and Creation
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Order checkout(Order order, PaymentMethod paymentMethod) {
        if (!validate(order)) {
            logger.warn("Order should have customer, order items, and payment type defined before being able to " +
                    "checkout the order.");
            return null;
        }

        // Set all order fields with proper values
        order.setPaymentMethod(paymentMethod);
        order.setSubmittedDate(new Date());
        order.setCost(cost(order));



        return create(order);
    }


    //Add New Order Item in Order
    private OrderItem newOrderItem(Order order, StoreItem item, int quantity) {

        return OrderItem.builder().storeItem(item).order(order).quantity(quantity).build();
    }

//Validation , Nullability and Store Validation Methods
//-----------------------------------------------------\\

    //Validate the order Based on Orders Requirements
    private boolean validate(Order order) {
        return order != null && !order.getOrderItems().isEmpty() && order.getAccount() != null && order.getStore()!=null;
    }

    //Check if the Items store id corresponds to the order store id
    private boolean checkStore(Order order, StoreItem item) {
        long orderStoreId =order.getStore().getId();
        long itemStoreId= item.getStore().getId();
        logger.info("Order_Store : {} Item store id: {}", orderStoreId,itemStoreId );
        if(orderStoreId!=itemStoreId) {
            logger.warn("Item not in store");
            return true;
        }

        return false;
    }


    // Check if the order has items in and check if Item has anything inside
    private boolean checkNullability(Order order, StoreItem item ) {
        if (order == null) {
            logger.warn("Order Not Initiated.");
            return true;
        }
        if (item == null) {
            logger.warn("Item Not Found.");
            return true;
        }
        return false;
    }

    // Stream through OrderItems and then through Items and get each Price multiply by quantity add to cost
    private BigDecimal cost(Order order){


        return order.getOrderItems().stream()
                .map(oi -> oi.getStoreItem().getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}

