package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Order;
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


}
