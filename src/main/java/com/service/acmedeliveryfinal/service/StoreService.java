package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import com.service.acmedeliveryfinal.transfer.StoreDetailsDto;
import com.service.acmedeliveryfinal.transfer.StoreDto;
import com.service.acmedeliveryfinal.transfer.StoreItemDto;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface StoreService extends BaseService<Store>{
    Store getLazy(Long id);

    List<Store> getLazyAll();

    void addItems(Store store, Set<StoreItem> items);

    void addItem(Long id, StoreItem item);

    void removeItem(Long id, Long sid);

    void updateItem(Long id, StoreItem item);

    List<StoreCategory> getAllStoreCategories();

    List<KeyValue<Long,String>> getStoresDropdownList(String searchString);

    List<Store> getStoresByCategoryId(Long id);

    List<StoreItem> getProductsByStore(Long id);

    StoreItem getProduct(Long storeId,Long id);

    StoreItem getProduct(Long id);

    List<KeyValue<Long,String>> findPopularStores();

    //alternative approach returns a map
    Map<Long,String> findPopularStoresMap();

    List<KeyValue<Long,String>> findPopularStoresByCategory(String categoryName);

    List<KeyValue<Long,String>> findPopularStoresByCategory(Long categoryId);

    List<KeyValue<Long,String>> findPopularProducts();

    List<KeyValue<Long,String>> findPopularProductsByStore(Long id);

    List<StoreDetailsDto> getStoreDetailsDtos();

    StoreDetailsDto getStoreDetailsDto(Long id);

    StoreDto getStoreDto(Long id);



}
