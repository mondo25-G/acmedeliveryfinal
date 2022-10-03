package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.repository.StoreCategoryRepository;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import com.service.acmedeliveryfinal.transfer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "cacheManager", cacheNames = {"popular"}, keyGenerator = "CustomCacheKeyGenerator")
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {

    private final StoreRepository storeRepository;

    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }


    //Lazy loading methods for Store
    @Override
    public Store getLazy(Long id) {
        Store store = storeRepository.getLazy(id).orElseThrow(
                () -> new NoSuchElementException(String.format("There was no store found matching id %d.")));
        return store;
    }


    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryRepository.findAll();
    }

    //Service Get methods for store categories, stores by category, can be refactored to StoreService too.
    @Override
    public List<Store> getStoresByCategoryId(Long id) {
        return storeRepository.findStoresByCategoryId(id);
    }


    @Override
    public void addItem(Long id, StoreItem item) {
        Store store = storeRepository.findById(id).get();
        logger.info("Update Store: {} ", store);
        logger.info("With Item: {}", item);
        store.getStoreItems().add(item);
        item.setStore(store);
        storeRepository.save(store);
    }

    //Aggregate (StoreItem) get methods, helpers for seeding.

    @Override
    public StoreItem getProduct(Long storeId, Long id) {
        return storeRepository.findStoreItem(storeId, id);
    }

    @Override
    public StoreItem getProduct(Long id) {
        return storeRepository.findStoreItem(id);
    }


    //DTOs

    //Dropdownlist for searching stores by name or category
    @Override
    public List<KeyValue<Long, String>> getStoresDropdownList(String searchString) {
        List<KeyValue<Long, String>> dropdownList = new ArrayList<>();
        List<Store> searchResults = storeRepository.findStoresByNameOrCategory(searchString);
        searchResults.forEach(store -> dropdownList.add(new KeyValue<>(store.getId(), store.getStoreName())));
        return dropdownList;
    }


    //Business methods for popular stores
    @Override
    public List<KeyValue<Long, String>> findPopularStores() {
        return storeRepository.findTop10Stores();
    }

    @Override
    public List<KeyValue<Long, String>> findPopularStoresByCategory(Long categoryId) {
        return storeRepository.findTopStoresByCategory(categoryId);
    }

    //Business methods for popular store items
    @Override
    //@Cacheable(cacheNames = "popular")
    public List<PopularItemDto> findPopularProducts() {
        return storeRepository.findTop10StoreItems();
    }

    @Override
    public List<StoreDetailsDto> getStoreDetailsDtos() {
        List<Store> storesList = storeRepository.getLazyAll();
        List<StoreDetailsDto> storeDtoList = new ArrayList<>();
        for (Store store : storesList) {
            storeDtoList.add(createStoreDetailsDto(store));
        }
        return storeDtoList;
    }

    @Override
    public StoreDto getStoreDto(Long id) {
        Store store = getLazy(id);
        return createStoreDto(store);
    }


    //DTO creation
    private StoreDto createStoreDto(Store store) {
        return new StoreDto() {
            @Override
            public Long getId() {
                return store.getId();
            }
            @Override
            public String getStoreName() {
                return store.getStoreName();
            }
            @Override
            public String getEmailAddress() {
                return store.getEmailAddress();
            }

            @Override
            public String getAddress() {
                return store.getAddress();
            }

            @Override
            public String getCity() {
                return store.getCity();
            }

            @Override
            public String getPhoneNumber() {
                return store.getPhoneNumber();
            }

            @Override
            public List<StoreItemDto> getStoreItems() {
                List<StoreItemDto> siDto = new ArrayList<>();
                for (StoreItem storeItem:store.getStoreItems()){
                    siDto.add(createStoreItemDto(storeItem));
                }
                return siDto;
            }
        };
    }

    private StoreDetailsDto createStoreDetailsDto(Store store){
        return new StoreDetailsDto() {
            @Override
            public Long getId() {
                return store.getId();
            }
            @Override
            public String getStoreName() {
                return store.getStoreName();
            }


            @Override
            public String getStoreCategory(){
                return store.getCategory().getName();
            }
        };
    }

    private StoreItemDto createStoreItemDto(StoreItem storeItem) {
        return new StoreItemDto() {
            @Override
            public Long getStoreId() {
                return storeItem.getId();
            }

            @Override
            public Long getId() {
                return storeItem.getId();
            }

            @Override
            public String getItemName() {
                return storeItem.getItemName();
            }

            @Override
            public BigDecimal getPrice() {
                return storeItem.getPrice();
            }

            @Override
            public String getProductCategory() {
                return storeItem.getProductCategory().getName();
            }
        };
    }

    @CacheEvict(cacheNames = {"popular"}, allEntries = true)
    @Scheduled(cron = "0 0/2 22 * * ?") //start at 22:00 until 22.58
    public void evictCache() {
        logger.info("Evicting popular cache contents.");
    }
}