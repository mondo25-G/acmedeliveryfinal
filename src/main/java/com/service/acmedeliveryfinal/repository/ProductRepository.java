package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product , Long> {

    Product findBySerial(String serial);

}
