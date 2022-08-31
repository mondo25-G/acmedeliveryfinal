package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product , Long> {

    Product findBySerial(String serial);
//    @Query
//    List<Product> topProductsByRanking(Integer rankingThreshold);

}
