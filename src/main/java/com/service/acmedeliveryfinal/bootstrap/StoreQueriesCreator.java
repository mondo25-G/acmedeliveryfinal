package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.service.StoreService;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import com.service.acmedeliveryfinal.transfer.PopularItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class StoreQueriesCreator extends BaseComponent implements CommandLineRunner {
    private final StoreService storeService;

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

        //GET MOST POPULAR STORES (BASED ON ORDERS) with List approach
        List<KeyValue<Long,String>> topStores = storeService.findPopularStores();

        topStores.forEach(s->logger.info("Popular store: {}",s));

        //GET MOST POPULAR STORES (BASED ON ORDERS) BY CATEGORY ID
        List<KeyValue<Long,String>> topStoresCatId = storeService.findPopularStoresByCategory(1L);

        logger.info("Most popular stores based on total store orders descending, store id ascending for specific category Id");

        topStoresCatId.forEach(s->logger.info("Popular (categoryId=1-PIZZA): {}",s));

        //GET MOST POPULAR PRODUCTS (BASED ON ORDERS)
        logger.info("Most popular products based on orders, store id ascending");
        List<PopularItemDto> topProds = storeService.findPopularProducts();
        topProds.forEach(s->logger.info("Popular prod:{}",s));

    }
}
