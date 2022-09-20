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

    @Query("select distinct s from Store s join fetch s.category c join fetch s.storeItems si join fetch si.productCategory where s.storeName like %:name% or c.name like %:name% order by s.storeName,s.id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Store> findStoresByNameOrCategory(String name);

    @Query("select distinct s from Store s join fetch s.storeItems si join fetch s.category join fetch si.productCategory where s.category.id =:id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Store> findStoresByCategoryId(Long id);


}
