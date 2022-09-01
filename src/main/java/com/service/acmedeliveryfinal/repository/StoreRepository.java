package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query(value = "SELECT s FROM Store s WHERE s.storeCategory = ?1")
    List<Store> getStoresByCategory(String category);
    @Query(value = "SELECT s FROM Store s WHERE s.storeName = ?1")
    List<Store> getStoresByName(String name);
}
