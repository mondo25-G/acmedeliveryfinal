package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.transfer.KeyValue;
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


    //Jpa helper queries.

    @Query("select distinct s from StoreItem s where s.store.id=:id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<StoreItem> findStoreItemsByStoreId(Long id);

    @Query("select distinct s from StoreItem s where s.store.id=:id and s.id=:sid")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    StoreItem findStoreItem(Long id,Long sid);

    @Query("select distinct s from StoreItem s where s.id=:sid")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    StoreItem findStoreItem(Long sid);

    //Native query methods to find popular stores. passed as DTOs.

   @Query(name="Store.findTop10Stores",nativeQuery = true)
    List<KeyValue<Long,String>> findTop10Stores();

    //find popular stores per category based on category Id (Long)

    @Query(name="Store.findTop10StoresByCategoryId",nativeQuery = true)
    List<KeyValue<Long,String>> findTopStoresByCategory(Long categoryId);

    //find popular stores per category based on category Name (Long)

    @Query(name="Store.findTop10StoresByCategoryName", nativeQuery = true)
    List<KeyValue<Long,String>> findTopStoresByCategory(String category);

    //Native query methods to find popular store products. passed as DTOs.

    //find all popular store products
    @Query(name="StoreItem.findTop10Products",nativeQuery = true)
    List<KeyValue<Long,String>> findTop10StoreItems();

    //find all popular store products per store.
    @Query(name="StoreItem.findTop10ProductsByStoreId",nativeQuery = true)
    List<KeyValue<Long,String>> findTop10StoreItemsByStore(Long id);
}
