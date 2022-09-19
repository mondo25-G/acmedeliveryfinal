package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.ProductCategory;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.service.ProductCategoryService;
import com.service.acmedeliveryfinal.service.StoreCategoryService;
import com.service.acmedeliveryfinal.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
@Order(1)
public class StoreContentCreator extends BaseComponent implements CommandLineRunner {
    private final ProductCategoryService productCategoryService;
    private final StoreService storeService;
    private final StoreCategoryService storeCategoryService;

    @Override
    public void run(String... args) {
        List<ProductCategory> productCategories = List.of(
                ProductCategory.builder().name("Pizza").build(),
                ProductCategory.builder().name("Burgers").build(),
                ProductCategory.builder().name("Soft Drinks").build(),
                ProductCategory.builder().name("Desserts").build(),
                ProductCategory.builder().name("PizzaRosse").build(),
                ProductCategory.builder().name("Dolci").build(),
                ProductCategory.builder().name("Beef Burgers").build(),//7
                ProductCategory.builder().name("Signature Burgers").build(),//8
                ProductCategory.builder().name("Chicken Burgers").build(),//9
                ProductCategory.builder().name("Greek Style Burgers").build()//10
        );
        logger.info("Create Product Categories:{}", productCategoryService.createAll(productCategories));

        List<StoreCategory> storeCategories = List.of(
                StoreCategory.builder().name("Pizzeria").build(),
                StoreCategory.builder().name("Burgers").build(),
                StoreCategory.builder().name("CoffeeShop").build(),
                StoreCategory.builder().name("Souvlakia").build()
        );
        logger.info("Create Store Categories:{}", storeCategoryService.createAll(storeCategories));


        StoreItem portofino1= StoreItem.builder().itemName("Margherita").price(BigDecimal.valueOf(10.00)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino2= StoreItem.builder().itemName("Portofino").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino3=StoreItem.builder().itemName("Pepperoni Classic").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino4=StoreItem.builder().itemName("Vegetarian").price(BigDecimal.valueOf(100.00)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino5=StoreItem.builder().itemName("Coca-Cola 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build();
        StoreItem portofino6=StoreItem.builder().itemName("Coca-Cola light 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build();
        StoreItem portofino7=StoreItem.builder().itemName("Panna Cotta").price(BigDecimal.valueOf(5.00)).productCategory(productCategoryService.get(4L)).build();
        StoreItem portofino8=StoreItem.builder().itemName("Pavlova").price(BigDecimal.valueOf(5.00)).productCategory(productCategoryService.get(4L)).build();

        Store portofino = storeService.create(Store.builder().storeName("Portofino").city("Kifisia").address("Levidou 16,14562").phoneNumber("21000000").emailAddress("portofino@gmail.com").category(storeCategoryService.get(1L)).build());

        storeService.addItem(portofino.getId(),portofino1);
        logger.info("Added portofino menu item 1");
        storeService.addItem(portofino.getId(),portofino2);
        logger.info("Added portofino menu item 2");
        storeService.addItem(portofino.getId(),portofino3);
        logger.info("Added portofino menu item 3");
        storeService.addItem(portofino.getId(),portofino4);
        logger.info("Added portofino menu item 4");
        storeService.addItem(portofino.getId(),portofino5);
        logger.info("Added portofino menu item 5");
        storeService.addItem(portofino.getId(),portofino6);
        logger.info("Added portofino menu item 6");
        storeService.addItem(portofino.getId(),portofino7);
        logger.info("Added portofino menu item 7");
        storeService.addItem(portofino.getId(),portofino8);
        logger.info("Added portofino menu item 8");

        Store sogno = storeService.create(Store.builder().storeName("Sogno").city("Kifisia").address("Fragkopoulou 30,14561").phoneNumber("211000000").emailAddress("sogno@gmail.com").category(storeCategoryService.get(1L)).build());

        List<StoreItem> sognoMenu = List.of(
                StoreItem.builder().itemName("Margherita").price(BigDecimal.valueOf(7.60)).productCategory(productCategoryService.get(5L)).build(),
                StoreItem.builder().itemName("Special").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(5L)).build(),
                StoreItem.builder().itemName("Americana").price(BigDecimal.valueOf(12.50)).productCategory(productCategoryService.get(5L)).build(),
                StoreItem.builder().itemName("Coca-Cola 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build(),
                StoreItem.builder().itemName("Coca-Cola light 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build(),
                StoreItem.builder().itemName("Tiramissu").price(BigDecimal.valueOf(7.90)).productCategory(productCategoryService.get(6L)).build(),
                StoreItem.builder().itemName("Duo Mousse").price(BigDecimal.valueOf(9.00)).productCategory(productCategoryService.get(6L)).build())
                ;

        storeService.addItems(storeService.get(2L), sognoMenu);

        logger.info("Added sogno menu items");

        Store burgeria = storeService.create(Store.builder().storeName("Burgheria").city("Irakleio").address("Elektras Apostolou 62, 14122").phoneNumber("211000000").emailAddress("burgheria@gmail.com").category(storeCategoryService.get(2L)).build());

        List<StoreItem> burgeriaMenu = List.of(
                StoreItem.builder().itemName("Classic Burger").price(BigDecimal.valueOf(6.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("Cheese & Bacon Burger").price(BigDecimal.valueOf(7.50)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("The old school Burger").price(BigDecimal.valueOf(10.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("Italian job burger").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(8L)).build(),
                StoreItem.builder().itemName("Lusso blue burger").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(8L)).build(),
                StoreItem.builder().itemName("Pure chicken burger").price(BigDecimal.valueOf(6.90)).productCategory(productCategoryService.get(9L)).build(),
                StoreItem.builder().itemName("Avocado juicy chicken burger").price(BigDecimal.valueOf(7.50)).productCategory(productCategoryService.get(9L)).build())
                ;

        storeService.addItems(storeService.get(3L), burgeriaMenu);

        Store burgery = storeService.create(Store.builder().storeName("Burgery").city("Peuki").address("Dimokratias 3, 151212").phoneNumber("211000000").emailAddress("burgery@gmail.com").category(storeCategoryService.get(2L)).build());

        List<StoreItem> burgeryMenu = List.of(
                StoreItem.builder().itemName("Classic cheeseburger").price(BigDecimal.valueOf(7.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("Blue bacon burger").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("Just must burger").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("Greek burger gyros xoirinos").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(10L)).build(),
                StoreItem.builder().itemName("Greek burger gyros kotopoulo").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(10L)).build(),
                StoreItem.builder().itemName("Billy the kid burger").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(9L)).build(),
                StoreItem.builder().itemName("Al Capone burger").price(BigDecimal.valueOf(9.60)).productCategory(productCategoryService.get(9L)).build())
                ;

        storeService.addItems(storeService.get(4L), burgeriaMenu);


        storeService.addItem(1L, StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build());
        logger.info("Added BBQ pizza to Portofino");

        storeService.removeItem(1L, 5L); //ok no cascade.
        logger.info("Removed Coca-Cola 330 ml from Portofino");

        //needs storeItem repository
        //storeService.updateItem(1L,StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(14.00)).productCategory(productCategoryService.get(1L)).build() );

        //ok cascades only to storeItems.
        //storeService.delete(storeService.get(1L));

    }
}

