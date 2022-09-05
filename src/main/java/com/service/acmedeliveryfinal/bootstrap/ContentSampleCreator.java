package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ContentSampleCreator extends BaseComponent implements CommandLineRunner {
    private final StoreService storeService;

    //@formatter: off
    //create test. succeeds

    @Override
    public void run(String... args) throws Exception {
        storeService.create(Store.builder().storeName("Store A").storeCategory(StoreCategory.BURGERS).build());
        storeService.create(Store.builder().storeName("Store B").storeCategory(StoreCategory.BURGERS).build());
        storeService.create(Store.builder().storeName("Store C").storeCategory(StoreCategory.PIZZERIA).build());
        storeService.create(Store.builder().storeName("Store D").storeCategory(StoreCategory.PIZZERIA).build());
        storeService.create(Store.builder().storeName("Store E").storeCategory(StoreCategory.PIZZERIA).build());
        storeService.create(Store.builder().storeName("Store F").storeCategory(StoreCategory.RESTAURANT).build());
        storeService.create(Store.builder().storeName("Store G").storeCategory(StoreCategory.RESTAURANT).build());
        storeService.create(Store.builder().storeName("Store H").storeCategory(StoreCategory.RESTAURANT).build());
        storeService.create(Store.builder().storeName("Store I").storeCategory(StoreCategory.BURGERS).build());

    }
    //@formatter: on

}
