package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.ProductCategory;

public interface ProductCategoryService extends BaseService<ProductCategory> {
    ProductCategory getByName(String name);
}
