package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Product;

import java.io.Serial;

public interface ProductService extends BaseService<Product> {
    Product findBySerial(String serial);
}