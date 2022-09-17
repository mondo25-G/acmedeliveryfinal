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
                ProductCategory.builder().name("Pizza Rosse").build(),
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
        ProductCategory pizzaRosse = productCategoryService.getByName("Pizza Rose");
        ProductCategory dolci = productCategoryService.getByName("Pizza Rose");

        StoreCategory pizzeria = storeCategoryService.getByName("Pizzeria");
        StoreCategory burgerHouse = storeCategoryService.getByName("Burger House");

        StoreItem portofinoItem1=StoreItem.builder().itemName("Margherita").price(BigDecimal.valueOf(10.00)).productCategory(pizzas).build();
        List<StoreItem> portofinoMenu = List.of(
                StoreItem.builder().itemName("Margherita").price(BigDecimal.valueOf(10.00)).productCategory(pizzas).build(),
                StoreItem.builder().itemName("Portofino").price(BigDecimal.valueOf(11.50)).productCategory(pizzas).build(),
                StoreItem.builder().itemName("Pepperoni Classic").price(BigDecimal.valueOf(11.50)).productCategory(pizzas).build(),
                StoreItem.builder().itemName("Vegetarian").price(BigDecimal.valueOf(100.00)).productCategory(pizzas).build(),
                StoreItem.builder().itemName("Coca-Cola 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(softDrinks).build(),
                StoreItem.builder().itemName("Coca-Cola light 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(softDrinks).build(),
                StoreItem.builder().itemName("Panna Cotta").price(BigDecimal.valueOf(5.00)).productCategory(desserts).build(),
                StoreItem.builder().itemName("Pavlova").price(BigDecimal.valueOf(5.00)).productCategory(desserts).build())
                ;
        List<StoreItem> sognoMenu = List.of(
                StoreItem.builder().itemName("Margherita").price(BigDecimal.valueOf(7.60)).productCategory(pizzaRosse).build(),
                StoreItem.builder().itemName("Special").price(BigDecimal.valueOf(11.50)).productCategory(pizzaRosse).build(),
                StoreItem.builder().itemName("Americana").price(BigDecimal.valueOf(12.50)).productCategory(pizzaRosse).build(),
                StoreItem.builder().itemName("Coca-Cola 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(softDrinks).build(),
                StoreItem.builder().itemName("Coca-Cola light 330 ml").price(BigDecimal.valueOf(1.70)).productCategory(softDrinks).build(),
                StoreItem.builder().itemName("Tiramissu").price(BigDecimal.valueOf(7.90)).productCategory(dolci).build(),
                StoreItem.builder().itemName("Duo Mousse").price(BigDecimal.valueOf(9.00)).productCategory(dolci).build())
        ;




        Store portofino = storeService.create(Store.builder().storeName("Portofino").city("Kifisia").address("Levidou 16,14562").phoneNumber("21000000").emailAddress("portofino@gmail.com").category(pizzeria).build());


        Store sogno = storeService.create(Store.builder().storeName("Sogno").city("Kifisia").address("Fragkopoulou 30,14561").phoneNumber("211000000").emailAddress("sogno@gmail.com").category(pizzeria).build());

        logger.info("Creating Stores : {} {}", portofino,sogno);
        storeService.addItems(storeService.get(1L), portofinoMenu);
        storeService.addItem(1L, StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(11.50)).productCategory(pizzas).build());
        storeService.removeItem(1L, 5L);
        storeService.addItems(storeService.get(2L), sognoMenu);

    }
}

