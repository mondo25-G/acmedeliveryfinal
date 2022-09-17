package com.service.acmedeliveryfinal.repository;


import com.service.acmedeliveryfinal.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    ProductCategory getByName(String name);
}
