package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.transfer.AccountOrderHeaderDto;
import com.service.acmedeliveryfinal.transfer.OrderDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {


    @Query("select distinct o from Order o join fetch o.account join fetch o.orderItems oi join fetch oi.storeItem i " +
            " where o.id = :id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    Order getLazy(Long id);

    @Query("select distinct  o from Order o join fetch o.orderItems oi join fetch oi.storeItem join fetch o.account c where o.account.id = :id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Order> findByCustomer(Long id);


    @Query(value = "select o.id as orderId,s.storeName as storeName,a.address as deliveryAddress,o.submittedDate as submittedDate,o.cost as cost from orders o inner join accounts a on a.id=o.account_id inner join stores s on o.store_id=s.id where a.id=?1",nativeQuery = true)
    List<AccountOrderHeaderDto> getOrdersByAccount(Long id);
    @Query(value = "select o.order_id as orderId,s.itemname as ItemName,o.price as price,o.quantity as quantity from orderItems o inner join storeitems s on s.id=o.storeitem_id where o.order_id=?1",nativeQuery = true)
    List<OrderDetailsDto> getOrderItemsByOrder(Long id);

}
