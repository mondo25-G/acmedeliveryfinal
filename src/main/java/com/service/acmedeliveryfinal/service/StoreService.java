package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.transfer.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface StoreService extends BaseService<Store>{
    Store getLazy(Long id);

    void addItem(Long id, StoreItem item);


    List<StoreDetailsDto> getStoreDetailsDtos();

    StoreDto getStoreDto(Long id);

    List<StoreCategory> getAllStoreCategories();

    List<Store> getStoresByCategoryId(Long id);

    List<KeyValue<Long,String>> findPopularStores();

    List<KeyValue<Long,String>> findPopularStoresByCategory(Long categoryId);

    List<PopularItemDto> findPopularProducts();

    List<KeyValue<Long,String>> getStoresDropdownList(String searchString);

    StoreItem getProduct(Long storeId,Long id);

    StoreItem getProduct(Long id);



}
