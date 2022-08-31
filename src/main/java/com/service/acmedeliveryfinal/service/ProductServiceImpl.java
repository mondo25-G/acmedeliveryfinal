package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Product;
import com.service.acmedeliveryfinal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
}