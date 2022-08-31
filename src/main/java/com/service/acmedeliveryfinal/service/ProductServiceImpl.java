package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Product;
import com.service.acmedeliveryfinal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public JpaRepository<Product , Long> getRepository(){
        return productRepository;
    }

    @Override
    public Product findBySerial(final String serial){
        return productRepository.findBySerial(serial);
    }

    public List<Product> topProductsByRanking(Integer rankingThreshold){
        List<Product> topProducts = new ArrayList<>();
        return topProducts;
    }
}