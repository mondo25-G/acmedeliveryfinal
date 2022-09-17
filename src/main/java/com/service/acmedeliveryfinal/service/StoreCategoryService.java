package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.StoreCategory;

public interface StoreCategoryService extends BaseService<StoreCategory> {
    StoreCategory getByName(String name);
}
