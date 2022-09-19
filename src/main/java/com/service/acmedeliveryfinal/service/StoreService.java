package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;

import java.util.List;


public interface StoreService extends BaseService<Store>{
    Store getLazy(Long id);

    List<Store> getLazyAll();

    void addItems(Store store, List<StoreItem> items);

    void addItem(Long id, StoreItem item);

    void removeItem(Long id, Long sid);

    void updateItem(Long id, StoreItem item);

    List<StoreCategory> getAllStoreCategories();
}
