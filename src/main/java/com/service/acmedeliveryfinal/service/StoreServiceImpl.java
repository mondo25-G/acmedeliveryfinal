package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.repository.StoreCategoryRepository;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{

    private final StoreRepository storeRepository;

    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public JpaRepository<Store, Long> getRepository() {return storeRepository;}


    //Lazy loading methods for Store
    @Override
    public Store getLazy(Long id) {
            Optional<Store> store = storeRepository.getLazy(id);
            if (store.isPresent()) {
                return store.get();
            }
            throw new NoSuchElementException(String.format("There was no store found matching id %d.", id));
    }

    @Override
    public List<Store> getLazyAll() {
        return storeRepository.getLazyAll();
    }


    //This can be retrieved through StoreCategoryService too, placed here for front end convenience / existing endpoint.
    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryRepository.findAll();
    }

    //Service Get methods for store categories, stores by category, can be refactored to StoreService too.
    @Override
    public List<Store> getStoresByCategoryId(Long id) {
        return storeRepository.findStoresByCategoryId(id);
    }



    //StoreItem crud methods.
    @Override
    public void addItems(Store store, Set<StoreItem> items) {
        store.setStoreItems(items);
        items.forEach(item -> item.setStore(store));
        storeRepository.save(store);
    }

    @Override
    public void addItem(Long id, StoreItem item) {
        Store store =storeRepository.findById(id).get();
        logger.info("Update Store: {} ",store );
        logger.info("With Item: {}",item);
        store.getStoreItems().add(item);
        item.setStore(store);
        storeRepository.save(store);
    }

    @Override
    public void removeItem(Long id, Long sid) {
        Store store =storeRepository.findById(id).get();
        store.getStoreItems().removeIf(storeItem -> Objects.equals(storeItem.getId(), sid));
        logger.info("Delete Item: {} ",store);
        storeRepository.save(store);
    }

    @Override
    public void updateItem(Long id, StoreItem item) {
        Store store =storeRepository.findById(id).get();
        logger.info("Update Store: {} ",store);
        logger.info("Update Item: {} ",item);

        store.getStoreItems().removeIf
                (storeItem -> Objects.equals(storeItem.getId(),item.getId()));
        store.getStoreItems().add(item);

        storeRepository.save(store);
    }

    //Aggregate (StoreItem) get methods, helpers for seeding.
    @Override
    public List<StoreItem> getProductsByStore(Long id) {
        return storeRepository.findStoreItemsByStoreId(id);
    }

    @Override
    public StoreItem getProduct(Long storeId, Long id) {
        return storeRepository.findStoreItem(storeId,id);
    }

    @Override
    public StoreItem getProduct(Long id) {
        return storeRepository.findStoreItem(id);
    }


    //DTOs

    //Dropdownlist for searching stores by name or category
    @Override
    public List<KeyValue<Long, String>> getStoresDropdownList(String searchString) {
        List<KeyValue<Long,String>> dropdownList = new ArrayList<>();
        List<Store> searchResults=storeRepository.findStoresByNameOrCategory(searchString);
        searchResults.forEach(store->dropdownList.add(new KeyValue<>(store.getId(),store.getStoreName())));
        return dropdownList;
    }

    //Business methods for popular stores
    @Override
    public List<KeyValue<Long,String>> findPopularStores(){
        return storeRepository.findTop10Stores();
    }

    @Override
    public List<KeyValue<Long,String>> findPopularStoresByCategory(String categoryName) {
        return storeRepository.findTopStoresByCategory(categoryName);
    }

    @Override
    public List<KeyValue<Long,String>> findPopularStoresByCategory(Long categoryId) {
        return storeRepository.findTopStoresByCategory(categoryId);
    }

    //Business methods for popular store items
    @Override
    public List<KeyValue<Long, String>> findPopularProducts() {
        return storeRepository.findTop10StoreItems();
    }

    @Override
    public List<KeyValue<Long, String>> findPopularProductsByStore(Long id) {
        return storeRepository.findTop10StoreItemsByStore(id);
    }


}
