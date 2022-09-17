package com.service.acmedeliveryfinal.repository;


import com.service.acmedeliveryfinal.domain.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {
    StoreCategory getByName(String name);
}
