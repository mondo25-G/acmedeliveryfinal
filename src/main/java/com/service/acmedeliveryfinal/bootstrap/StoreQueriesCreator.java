package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.ProductCategory;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.service.ProductCategoryService;
import com.service.acmedeliveryfinal.service.StoreCategoryService;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class StoreQueriesCreator extends BaseComponent implements CommandLineRunner {

    private final ProductCategoryService productCategoryService;
    private final StoreService storeService;
    private final StoreCategoryService storeCategoryService;

    @Override
    public void run(String... args) throws Exception {

        Store fatto = storeService.getLazy(4L);

        logger.info("store : {}", fatto.getStoreName());

        logger.info("store category: {}", fatto.getCategory());

        logger.info("store items: {}", fatto.getStoreItems());

        logger.info("{} :",fatto );

        logger.info("{}",storeService.getAllStoreCategories());

        List<KeyValue<Long,String>> search = storeService.getStoresDropdownList("BURG");

        search.forEach(s->logger.info("{}",s));

        List<Store> byCat=storeService.getStoresByCategoryId(1L);

        logger.info("Stores by category id=1, {}",byCat);

        //GET MOST POPULAR STORES (BASED ON ORDERS)
        List<Store> topStores = storeService.findPopularStores();

        logger.info("Most popular stores based on total store orders descending, store id ascending");
        topStores.forEach(s->logger.info("Store Name: {}", s.getStoreName()));


        //GET MOST POPULAR STORES (BASED ON ORDERS) BY CATEGORY ID
        List<Store> topStoresCatId = storeService.findPopularStoresByCategory(1L);

        logger.info("Most popular stores based on total store orders descending, store id ascending for specific category Id");

        topStoresCatId.forEach(s->logger.info("Popular (categoryId=1-PIZZA): {}",s.getStoreName()));

        //GET MOST POPULAR STORES (BASED ON ORDERS) BY CATEGORY NAME
        List<Store> topStoresCatName = storeService.findPopularStoresByCategory("BURGERS");

        logger.info("Most popular stores based on total store orders descending, store id ascending for specific category Id");

        topStoresCatName.forEach(s->logger.info("Popular (categoryName=BURGERS): {}",s.getStoreName()));

    }
}
