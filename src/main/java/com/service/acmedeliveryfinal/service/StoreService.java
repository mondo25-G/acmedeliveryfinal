package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;

import java.util.List;

public interface StoreService extends BaseService<Store>{
    List<Store> getStoresByName(String name);

    List<Store> getStoresByCategory(StoreCategory storeCategoryEnum);
}
