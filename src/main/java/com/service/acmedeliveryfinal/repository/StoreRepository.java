package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query("select distinct s from Store s join fetch s.storeItems si join fetch s.category join fetch si.productCategory where s.id = :id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    Optional<Store> getLazy(Long id);

    @Query("select distinct s from Store s join fetch s.storeItems si join fetch s.category join fetch si.productCategory")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Store> getLazyAll();

    @Query("select distinct s from Store s join fetch s.category c join fetch s.storeItems si join fetch si.productCategory where s.storeName like :name% or c.name like :name% order by s.storeName,s.id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Store> findStoresByNameOrCategory(String name);

    @Query("select distinct s from Store s join fetch s.storeItems si join fetch s.category join fetch si.productCategory where s.category.id =:id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Store> findStoresByCategoryId(Long id);

    @Query(value = "with storeOrders as (select count(s.id) as ordersPlaced, s.id from stores s inner join orders o on s.id=o.store_id group by s.id order by count(s.id),s.id) select s.* from stores s inner join storeOrders c on s.id=c.id  order by c.ordersPlaced desc,c.id fetch first 10 rows only",nativeQuery = true)
    List<Store>findTop10Stores();

    //find popular stores per category based on category Id (Long)
    @Query(value = "with storeOrders as (select count(s.id) as ordersPlaced, s.id from stores s inner join orders o on s.id=o.store_id group by s.id order by count(s.id),s.id) select s.* from stores s inner join storeOrders c on s.id=c.id inner join store_categories sc on sc.id=s.storecategory_id where sc.id=?1 order by c.ordersPlaced desc,c.id fetch first 10 rows only",nativeQuery = true)
    List<Store>findTopStoresByCategory(Long categoryId);

    //find popular stores per category based on category id (Long)
    @Query(value = "with storeOrders as (select count(s.id) as ordersPlaced, s.id from stores s inner join orders o on s.id=o.store_id group by s.id order by count(s.id),s.id) select s.* from stores s inner join storeOrders c on s.id=c.id inner join store_categories sc on sc.id=s.storecategory_id where sc.name=?1 order by c.ordersPlaced desc,c.id fetch first 10 rows only",nativeQuery = true)
    List<Store>findTopStoresByCategory(String category);
}
