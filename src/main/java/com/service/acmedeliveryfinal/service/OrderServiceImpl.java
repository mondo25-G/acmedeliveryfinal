package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.*;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import com.service.acmedeliveryfinal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    private final OrderRepository orderRepository;

    private final StoreService storeService;

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }


    //Methods for the Initiation, Update, and Creation of Order

    @Override
    public Order initiateOrder(Store store, Account account) {

        if (account == null){throw new IllegalStateException("Customer cannot be null");}
        logger.info("Initiating Order for {}  " , account);
        return Order.builder().account(account).store(store).orderItems(new HashSet<>()).build();

    }

    //Add new Item to Order
    @Override
    public Order addItem(Order order, StoreItem item, int quantity) {
        logger.info("Item: {} ", item.getId());

        if (checkNullability(order, item)) {return order;}

        //clear cart on store change before adding new item
        if (checkStoreChange(order, item)) {
            if (!order.getOrderItems().isEmpty()){
                order.getOrderItems().removeAll(order.getOrderItems());
            }
            order.setStore(item.getStore());
            return addItem(order,item,quantity);
        }

        boolean increasedQuantity = false;

        // If product is already in order, increase quantity
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

        logger.debug("StoreItem[{}] added to Order[{}]", item, order);
        return order;
    }



    //Update the Item in the Order , Nullability, Store and Quantity validation
    @Override
    public Order updateItem(Order order, StoreItem item, int quantity) {

        if (checkNullability(order, item)) {return order;}
        //if (checkStoreChange(order, item)) {return order;} //unnecessary?
        if (quantity<1){removeItem(order, item); return order;}

        order.getOrderItems().removeIf(oi -> oi.getStoreItem().getId().equals(item.getId()));
        order.getOrderItems().add(newOrderItem(order, item, quantity));

        logger.debug("Product[{}] updated in Order[{}]", item, order);
        return order;
    }



    //Remove Item from Order, Nullability and Store validation
    @Override
    public Order removeItem(Order order, StoreItem item) {
        if (checkNullability(order, item)) {return order;}
        //if (checkStoreChange(order, item)) {return order;} //unnecessary?

        order.getOrderItems().removeIf(oi -> oi.getStoreItem().getId().equals(item.getId()));
        logger.debug("Product[{}] removed from Order[{}]", item, order);
        return order;
    }


    // Order Validation, Set proper values to fields and finalize order by create (save in database)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Order checkout(Order order, PaymentMethod paymentMethod) {

        // Validate order with validate method
        if (!validate(order)) {
            logger.warn("Order should have customer, order items, and payment type defined before being able to " +
                    "checkout the order.");
            return null;
        }

        // Set  order fields with proper values
        order.setPaymentMethod(paymentMethod);
        order.setSubmittedDate(new Date());
        order.setCost(cost(order));

        // Create Order
        return create(order);
    }

    //Add New Order Item in Order
    private OrderItem newOrderItem(Order order, StoreItem storeItem, int quantity) {

        StoreItem item = storeService.getProduct(storeItem.getId());

        // Build Order Item with store item, quantity, order and price (price acquired from StoreItem)
        return OrderItem.builder().storeItem(item).order(order).quantity(quantity).price(item.getPrice()).build();
    }

     //Validation , Nullability and Store Validation Methods\\
    //-------------------------------------------------------\\

    //Validate the order Based on Orders Requirements
    private boolean validate(Order order) {

        // Validation of Order Requirements
        return order != null && !order.getOrderItems().isEmpty() && order.getAccount() != null && order.getStore()!=null;
    }

    //Check if the Items store id corresponds to the order store id
    private boolean checkStoreChange(Order order, StoreItem storeItem) {
        StoreItem item = storeService.getProduct(storeItem.getId());

        // Get Order's Store id and Store Item store's id
        long orderStoreId =order.getStore().getId();
        long itemStoreId= item.getStore().getId();
        logger.info("Order_Store : {} Item store id: {}", orderStoreId,itemStoreId );

        // Validation if id's are equal
        if(orderStoreId!=itemStoreId) {
            logger.warn("Item not in store");
            return true;
        }

        return false;
    }


    // Check if the order has items in and check if Item has anything inside
    private boolean checkNullability(Order order, StoreItem item ) {

        //Check if Order is not null
        if (order == null) {
            logger.warn("Order Not Initiated.");
            return true;
        }

        // Check if Item is not null
        if (item == null) {
            logger.warn("Item Not Found.");
            return true;
        }
        return false;
    }

    // Stream through OrderItems and then through Items and get each item's Price and multiply by quantity add to cost
    private BigDecimal cost(Order order){

        return order.getOrderItems().stream()
                .map(oi -> oi.getStoreItem().getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public Order getLazy(Long id) {

        Order order= orderRepository.getLazy(id);
        if (!orderRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("There was no order found matching id %d.", id));
        }
        return order;
    }


    @Override
    public List<Order> getAllByAccount(Long id) {

        return orderRepository.findByCustomer(id);
    }

}

