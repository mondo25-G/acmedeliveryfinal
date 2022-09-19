package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.repository.StoreCategoryRepository;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{

    private final StoreRepository storeRepository;

    private final StoreCategoryRepository storeCategoryRepository;
    @Override
    public JpaRepository<Store, Long> getRepository() {return storeRepository;}


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

    @Override
    public void addItems(Store store, List<StoreItem> items) {
        for (StoreItem item : items)
        {
            store.getStoreItems().add(item);
            item.setStore(store);
        }
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

    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryRepository.findAll();
    }
}
