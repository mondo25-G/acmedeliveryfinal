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
                ProductCategory.builder().name("Dolci").build()
        );
        logger.info("Create Product Categories:{}", productCategoryService.createAll(productCategories));

        List<StoreCategory> storeCategories = List.of(
                StoreCategory.builder().name("Pizzeria").build(),
                StoreCategory.builder().name("Burger House").build()
        );
        logger.info("Create Store Categories:{}", storeCategoryService.createAll(storeCategories));

        ProductCategory pizzas = productCategoryService.getByName("Pizza");
        ProductCategory burgers = productCategoryService.getByName("Burger");
        ProductCategory softDrinks = productCategoryService.getByName("Soft Drinks");
        ProductCategory desserts = productCategoryService.getByName("Desserts");
        ProductCategory pizzaRosse = productCategoryService.getByName("PizzaRose");
        ProductCategory dolci = productCategoryService.getByName("Dolci");

        StoreCategory pizzeria = storeCategoryService.getByName("Pizzeria");
        StoreCategory burgerHouse = storeCategoryService.getByName("Burger House");

        StoreItem portofino1= StoreItem.builder().itemName("Margherita").price(BigDecimal.valueOf(10.00)).productCategory(pizzas).build();
        StoreItem portofino2= StoreItem.builder().itemName("Portofino").price(BigDecimal.valueOf(11.50)).productCategory(pizzas).build();
        StoreItem portofino3=StoreItem.builder().itemName("Pepperoni Classic").price(BigDecimal.valueOf(11.50)).productCategory(pizzas).build();
        StoreItem portofino4=StoreItem.builder().itemName("Vegetarian").price(BigDecimal.valueOf(100.00)).productCategory(pizzas).build();
        StoreItem portofino5=StoreItem.builder().itemName("Coca-Cola 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(softDrinks).build();
        StoreItem portofino6=StoreItem.builder().itemName("Coca-Cola light 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(softDrinks).build();
        StoreItem portofino7=StoreItem.builder().itemName("Panna Cotta").price(BigDecimal.valueOf(5.00)).productCategory(desserts).build();
        StoreItem portofino8=StoreItem.builder().itemName("Pavlova").price(BigDecimal.valueOf(5.00)).productCategory(desserts).build();

        Store portofino = storeService.create(Store.builder().storeName("Portofino").city("Kifisia").address("Levidou 16,14562").phoneNumber("21000000").emailAddress("portofino@gmail.com").category(pizzeria).build());

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

        Store sogno = storeService.create(Store.builder().storeName("Sogno").city("Kifisia").address("Fragkopoulou 30,14561").phoneNumber("211000000").emailAddress("sogno@gmail.com").category(pizzeria).build());

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

