package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Product;

import java.io.Serial;
import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product findBySerial(String serial);
    List<Product> topProductsByRanking(Integer rankingThreshold);
}